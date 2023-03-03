package cc.niushuai.project.shuaipush.biz.sms.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Accessors(chain = true)
@Table(name = "ns_sms")
@Entity
public class NsSms {

    @Id
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "create_time")
    private Date createTime;
}