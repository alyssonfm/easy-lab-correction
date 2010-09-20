package br.edu.les.easyCorrection.samples.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import junit.framework.TestCase;
import org.junit.Assert;

public class TesteUnit extends TestCase{
	
    public void testAdiciona() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, SecurityException, NoSuchMethodException, InstantiationException {
    	Class partypes[] = new Class[2];
		partypes[0] = Integer.TYPE;
		partypes[1] = Integer.TYPE;
		
		Object arglist[] = new Object[2];
		arglist[0] = new Integer(36);
		arglist[1] = new Integer(47);
		
    	Class classe = Class.forName(ReflectionTeste.caminhoClasseTestada);
    	Object inst = classe.getConstructor().newInstance();
    	Method meth = classe.getMethod("umMetodo", partypes);
        Assert.assertEquals(84, ((Integer) meth.invoke(inst, arglist)).intValue());
    }

}
