package com.jdc.shop.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Feature implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "feature_name", nullable = false)
	private String name;
	@Column(name = "feature_value", nullable = false)
	private String feature;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	@Override
	public int hashCode() {
		return Objects.hash(feature, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feature other = (Feature) obj;
		return Objects.equals(feature, other.feature) && Objects.equals(name, other.name);
	}

}
