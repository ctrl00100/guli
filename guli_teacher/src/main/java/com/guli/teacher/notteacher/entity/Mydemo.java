package com.guli.teacher.notteacher.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * mydemo
 * </p>
 *
 * @author guli
 * @since 2020-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Mydemo对象", description="mydemo")
public class Mydemo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "ID")
    private Float w;

    @ApiModelProperty(value = "ID")
    private Float t;

    @ApiModelProperty(value = "名称")
    private String s;

    @ApiModelProperty(value = "ID")
    private Float l;

    @ApiModelProperty(value = "名称")
    private String m;

    @ApiModelProperty(value = "ID")
    private Float j;

    @ApiModelProperty(value = "ID")
    private String h;

//    @ApiModelProperty(value = "创建时间")
    @ApiModelProperty(value = "创建时间", example = "2019-01-01 8:00:00")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtCreate;

//    @ApiModelProperty(value = "更新时间")
    @ApiModelProperty(value = "更新时间", example = "2019-01-01 8:00:00")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
