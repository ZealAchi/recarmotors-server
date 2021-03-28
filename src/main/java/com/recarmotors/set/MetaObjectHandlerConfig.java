package com.recarmotors.set;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    System.out.println("Insertar relleno de entidad de método");
    setFieldValByName("testDate", new Date(), metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    System.out.println("Método de actualización de llenado de entidades");
  }
}
