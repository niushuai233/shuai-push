/*
 * Copyright (C) 2023 niushuai233 niushuai.cc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.niushuai.project.shuaipush.biz.sms.controller;

import cc.niushuai.project.shuaipush.biz.sms.entity.NsSms;
import cc.niushuai.project.shuaipush.biz.sms.repository.SmsRepository;
import cc.niushuai.project.shuaipush.common.base.Result;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页controller
 *
 * @author niushuai233
 * @date 2023/3/3 11:24
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource
    private SmsRepository smsRepository;

    @GetMapping("/leishen")
    public Result<?> leiShen() {
        NsSms top = smsRepository.findTopByMessageLikeOrderByCreateTimeDesc("%雷神%");
        if (null == top) {
            return Result.error("暂未收到短信, 请稍后刷新页面");
        }
        return Result.success(top);
    }
}
