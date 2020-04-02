package com.etc.test;

import com.etc.library.domain.Type;
import com.etc.library.service.TypeService;
import com.etc.library.service.impl.TypeServiceImpl;
import org.junit.Test;

public class TypeTest {

    @Test
    public void testAddType() {
        TypeService typeService = new TypeServiceImpl();
        Type type = new Type("自然科学");
        System.out.println(typeService.addType(type));
    }

    @Test
    public void testQueryAllType() {
        TypeService typeService = new TypeServiceImpl();
        //Type type = new Type("自然科学");
        System.out.println(typeService.queryAllType());
    }
}
