package br.edu.ufcg.easyLabCorrection.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.ufcg.easyLabCorrection.exceptions.NonexistantAttributeException;
import br.edu.ufcg.easyLabCorrection.pojo.user.UserGroup;
import flex.messaging.io.MessageSerializer;
import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.ActionMessage;
import flex.messaging.io.amf.AmfMessageSerializer;
import flex.messaging.io.amf.MessageBody;
import flex.messaging.messages.RemotingMessage;

/**
 * Class that contains some functions that are used by several classes of system
 * ELC.<br>
 * 
 * @author Alysson Filgueira, Augusto Queiroz e Demetrio Gomes.<br>
 * @version 1.0 1-August-2011.<br>
 */
public class easyCorrectionUtil {

	/**
	 * Function which retrieves the current date.<br>
	 * 
	 * @return The current date.<br>
	 */
	public static Date getDataNow() {
		return getDataNowTimeZero(new Date());
	}

	/**
	 * Function which retrieves the current date, but with time zero (the zero
	 * hour today).<br>
	 * 
	 * @return The current date with time zero (the zero hour today).<br>
	 */
	public static Date getDataNowTimeZero(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(data.getTime());
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.AM_PM, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * Function which retrieves the date of real time.<br>
	 * 
	 * @return The date of real time.<br>
	 */
	public static Date getRealTime() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * Function which returns the date in the format "dd/mm/yyyy".<br>
	 * 
	 * @param dateTemp
	 *            The date temporary before to be formated.<br>
	 * @return The formated date.<br>
	 * @throws Exception
	 *             The exception which can to be launched.<br>
	 */
	public static Date formatData(String dateTemp) throws Exception {
		if (dateTemp == null || dateTemp.equals(""))
			return null;
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (java.util.Date) formatter.parse(dateTemp);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

	/**
	 * Function that Returns an attribute of the object passed, provided that it
	 * implements the method according to the convention to get the desired
	 * attribute.<br>
	 * 
	 * @param objectTemp
	 *            The object under which it invoked the get.<br>
	 * @param attribute
	 *            The desired attribute of the object.<br>
	 * @return The attribute value of object specified.<br>
	 * @throws AtributoNaoExisteExeption
	 *             If the specified attribute does not exist.<br>
	 */
	public static Object getAttribute(Object objectTemp, String attribute,
			boolean isBoolean) {
		try {
			Method m = null;
			if (isBoolean) {
				m = objectTemp.getClass().getMethod(
						"is" + correctsAttributeString(attribute));
			} else {
				m = objectTemp.getClass().getMethod(
						"get" + correctsAttributeString(attribute));
			}
			Object valor = m.invoke(objectTemp);
			return valor;
		} catch (Exception e) {
			throw new NonexistantAttributeException(
					ErrorMsgs.INEXISTENT_ATTRIBUTE.msg(attribute));
		}
	}

	/**
	 * Function that returns an attribute with the first letter in upper case,
	 * given a received attribute as a parameter.<br>
	 * 
	 * @param attribute
	 *            The attribute that will put the first letter in upper case.<br>
	 * @return The attribute with the first letter in upper case.<br>
	 */
	private static String correctsAttributeString(String attribute) {
		String firstLetter = String.valueOf(attribute.charAt(0));
		return attribute.replaceFirst(firstLetter, firstLetter.toUpperCase());
	}

	public static String getEmailMessage(UserGroup ug) {

		String message = "<BR>Dear "
				+ ug.getUser().getName()
				+ ",</BR>"
				+ "<BR></BR>"
				+ "<BR>Congratulations, an ELC "
				+ ug.getGroup().getName()
				+ " account has been created for you.</BR>"
				+ "<BR></BR>"
				+ "<BR>Enjoy the most from the system assignment submission facilities.</BR>"
				+ "<BR>Your access data are the following:</BR>"
				+ "<BR></BR>"
				+ "<BR>Login:</BR>"
				+ "<BR>"
				+ ug.getUser().getLogin()
				+ "</BR>"
				+ "<BR>Password:</BR><BR>"
				+ ug.getUser().getPassword()
				+ "</br>"
				+ "<p>Obs.: Please remember! Your password was randomly generated and it unstraferable, "
				+ "so we suggest you to update it imediatelly.</p>"
				+ "<BR>You can log in at</BR>"
				+ "<BR><a href=https://les.dsc.ufcg.edu.br:8443/EasyLabCorrection/>https://les.dsc.ufcg.edu.br:8443/EasyLabCorrection/</a></BR>"
				+ "<BR></BR>" + "<BR>Graciously,</BR>"
				+ "<BR>Easy Lab Correction Team</BR>";

		return message;
	}

	public static void copyDirectory(File srcDir, File dstDir)
			throws IOException {

		if (srcDir.isDirectory()) {
			if (!dstDir.exists()) {
				dstDir.mkdir();
			}
			String[] children = srcDir.list();
			for (int i = 0; i < children.length; i++) {
				copyDirectory(new File(srcDir, children[i]), new File(dstDir,
						children[i]));
			}
		} else {
			copyFile(srcDir, dstDir);
		}
	}

	private static void copyFile(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);

		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	// [ADSD] Utilizado para o projeto de Medi��o.
	public static void main(String[] args) throws Throwable {
		easyCorrectionUtil ecu = new easyCorrectionUtil();
		Object[] opParams = new Object[1];
		AmfRequestParameters params = new AmfRequestParameters(
				"easyCorrection", "submitAssignment", opParams);
		ecu.generate(params, "submitAssignment.txt");
		/*
		 * int count = 0; for (Assignment assignment : listAssignments) {
		 * count++;
		 * 
		 * }
		 */
	}

	public byte[] generate(AmfRequestParameters params, String nomeArquivo) {
		ActionMessage message = new ActionMessage();
		message.addBody(createMessageBody(params));

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		MessageSerializer serializer = createMessageSerializer(out);
		try {
			serializer.writeMessage(message);
		} catch (IOException e) {
			System.out.println("Algum erro Ocorreu!!!");
			// throw new AmfRequestGenerationException(e);
		}

		String strFileName = "D:/RequisicoesProjetoMedicao/" + nomeArquivo;
		BufferedOutputStream bos = null;

		try {
			FileOutputStream fos = new FileOutputStream(new File(strFileName));
			bos = new BufferedOutputStream(fos);
			bos.write(out.toByteArray());
		} catch (FileNotFoundException fnfe) {
			System.out.println("Specified file not found" + fnfe);
		} catch (IOException ioe) {
			System.out.println("Error while writing file" + ioe);
		} finally {
			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (Exception e) {
				}
			}
		}
		return out.toByteArray();
	}

	protected MessageBody createMessageBody(AmfRequestParameters params) {
		RemotingMessage message = new RemotingMessage();
		message.setHeader("HEADER_DS_ENDPOINT", "my-secure-amf");
		message.setHeader("HEADER_DS_ID", "1");
		message.setMessageId("1");
		message.setDestination(params.getDestination());
		message.setOperation(params.getOperation());
		message.setBody(params.getParameters());

		return new MessageBody(null, null, new RemotingMessage[] { message });
	}

	protected MessageSerializer createMessageSerializer(OutputStream out) {
		SerializationContext context = new SerializationContext();
		context.setSerializerClass(AmfMessageSerializer.class);
		SerializationContext.setSerializationContext(context);

		MessageSerializer serializer = context.newMessageSerializer();
		serializer.initialize(context, out, null);
		return serializer;
	}

}
