/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import java.time.DayOfWeek;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DayOfWeekConverter implements AttributeConverter<DayOfWeek, Integer>{

    @Override
    public Integer convertToDatabaseColumn(DayOfWeek attribute) {
        return(attribute == null ? null : attribute.getValue());
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public DayOfWeek convertToEntityAttribute(Integer dbData) {
        return (dbData == null ? null : DayOfWeek.of(dbData));
    }
    
}
