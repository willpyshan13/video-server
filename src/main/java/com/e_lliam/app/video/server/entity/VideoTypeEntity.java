package com.e_lliam.app.video.server.entity;

import javax.persistence.*;

/**
 * 视频分类
 * @author Landy
 *
 */
@Entity
public class VideoTypeEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long typeId;
	/**
	 * 父类别编号
	 */
	private Long typeParent;
	/**
	 * 类型名称(英文)
	 */
    @Column(length=40)
	private String typeName;
	/**
	 * 类型描述(中文)
	 */
    @Column(length = 50)
	private String typeDesc;

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getTypeParent() {
		return typeParent;
	}

	public void setTypeParent(Long typeParent) {
		this.typeParent = typeParent;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
}
