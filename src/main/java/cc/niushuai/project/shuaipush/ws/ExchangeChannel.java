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

package cc.niushuai.project.shuaipush.ws;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 转发通道
 *
 * @author niushuai233
 * @date 2024/6/21 16:24
 * @since 0.0.1
 */
@Slf4j
@ServerEndpoint(value = "/websocket/exchange")
public class ExchangeChannel {
    private static ConcurrentHashMap<String, ExchangeChannel> webSocketMap = new ConcurrentHashMap<>();
    private Session session;

    @OnMessage
    public void onMessage(String message) throws IOException {
        log.info("[websocket] exchange received message: {}", message);
        // 所有通道均发一遍
        for (ExchangeChannel value : webSocketMap.values()) {
            value.session.getBasicRemote().sendText(DateUtil.now() + ": " + message);
        }
    }
    // 连接打开
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig){
        // 保存 session 到对象
        this.session = session;
        log.info("[websocket] 新的连接：id={}", this.session.getId());
        webSocketMap.put(this.session.getId(), this);
    }

    // 连接关闭
    @OnClose
    public void onClose(CloseReason closeReason){
        log.info("[websocket] 连接断开：id={}，reason={}", this.session.getId(),closeReason);
        webSocketMap.remove(this.session.getId());
    }

    // 连接异常
    @OnError
    public void onError(Throwable throwable) throws IOException {

        log.info("[websocket] 连接异常：id={}，throwable={}", this.session.getId(), throwable.getMessage());
        webSocketMap.remove(this.session.getId());

        // 关闭连接。状态码为 UNEXPECTED_CONDITION（意料之外的异常）
        this.session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, throwable.getMessage()));
    }

}
