package com.jacky.utils.aop;

import java.lang.annotation.*;

//@Inherited annotation�����Ǳ���ע����class���������̳С��ಢ��������ʵ�ֵĽӿڼ̳�annotation�������������������صķ����̳�annotation
@Target({ ElementType.PARAMETER, ElementType.METHOD }) // FIELD:����������(�ֶ�)��METHOD:��������������PARAMETER:������������
@Retention(RetentionPolicy.RUNTIME) // ʹ�����meta-Annotation���Զ�Annotation�ġ��������ڡ����ƣ�������ʱ��Ч��������ʱ������
@Documented // ���Ա�����javadoc����Ĺ����ĵ���
public @interface Log {

	String module() default "";

	String methods() default "";
}
