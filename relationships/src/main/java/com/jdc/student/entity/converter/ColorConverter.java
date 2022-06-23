package com.jdc.student.entity.converter;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ColorConverter implements AttributeConverter<Color, String>{

	@Override
	public String convertToDatabaseColumn(Color color) {
		
		if(null != color) {
			return "%s,%s,%s".formatted(color.getRed(), color.getGreen(), color.getBlue());
		}
		
		return null;
	}

	@Override
	public Color convertToEntityAttribute(String str) {
		
		if(null != str && !str.isBlank()) {
			var color = str.split(",");
			return new Color(colorCode(color[0]), colorCode(color[1]), colorCode(color[2]));
		}
		
		return null;
	}

	private float colorCode(String string) {
		var value = new BigDecimal(string);
		return value.divide(new BigDecimal(255), 16, RoundingMode.HALF_UP).floatValue();
	}

}
