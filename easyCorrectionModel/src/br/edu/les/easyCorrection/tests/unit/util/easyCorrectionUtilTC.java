package br.edu.les.easyCorrection.tests.unit.util;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import br.edu.les.easyCorrection.pojo.acesso.Usuario;
import br.edu.les.easyCorrection.util.easyCorrectionUtil;

public class easyCorrectionUtilTC {

	@Test
	public void testAll() throws Exception {

		// isNULL
		Assert.assertFalse(easyCorrectionUtil.isNull(" IOIO"));
		Assert.assertFalse(easyCorrectionUtil.isNull(new Integer(1)));
		Assert.assertFalse(easyCorrectionUtil.isNull(""));
		Assert.assertTrue(easyCorrectionUtil.isNull(null));

		// isVazio
		Assert.assertTrue(easyCorrectionUtil.isVazio("       "));
		Assert.assertTrue(easyCorrectionUtil.isVazio(""));
		Assert.assertFalse(easyCorrectionUtil.isVazio("9090"));

		// isNUll || isVazio
		Assert.assertTrue(easyCorrectionUtil.isNullOrVazio(null));
		Assert.assertTrue(easyCorrectionUtil.isNullOrVazio(""));
		Assert.assertTrue(easyCorrectionUtil.isNullOrVazio("   "));
		Assert.assertFalse(easyCorrectionUtil.isNull(new Integer(1)));
		Assert.assertFalse(easyCorrectionUtil.isNull(" k "));

		// getDataNowTimeZero
		Date d = easyCorrectionUtil.getDataNowTimeZero(Calendar.getInstance()
				.getTime());
		Assert.assertEquals(0, d.getHours());
		Assert.assertEquals(0, d.getMinutes());
		Assert.assertEquals(0, d.getSeconds());

		// formataData
		// Date é uma classe deprecated, nao testaremos ela por enquanto pois
		// iremos mudar para Calendar

		// getAtributo
		Usuario us = new Usuario();
		us.setEmail("augustoqmacedo@gmail.com");
		us.setNome("Augusto");

		Assert.assertEquals("Augusto", easyCorrectionUtil.getAtributo(us,
				"nome", false));
		Assert.assertEquals("augustoqmacedo@gmail.com", easyCorrectionUtil
				.getAtributo(us, "email", false));

	}

}
