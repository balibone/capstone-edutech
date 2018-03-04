/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities.module;

import commoninfraentities.*;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Derian
 */
public class DayOfWeekAdapter extends XmlAdapter<Integer, DayOfWeek> {

    @Override
    public DayOfWeek unmarshal(Integer v) throws Exception {
        return DayOfWeek.of(v);
    }

    @Override
    public Integer marshal(DayOfWeek v) throws Exception {
        return v.getValue();
    }
    
}
