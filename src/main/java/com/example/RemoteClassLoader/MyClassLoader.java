package com.example.RemoteClassLoader;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.example.interfaces.iAnimal;

public class MyClassLoader {

	public void getJar() {

		try {
				
			//URL fileWeb = new URL("https://github.com/otamega93/remoteJarTest/blob/master/test.jar");
			//URL fileWeb = new URL("https://encodable.com/uploaddemo/files/test.jar");

			URL fileWeb = new URL("file:\\C:\\Users\\mapaez\\Desktop\\test.jar");
			
			URL urlWeb = fileWeb.toURI().toURL();
			URL[] urlsWeb = new URL[] { urlWeb };

			ClassLoader clWeb = new URLClassLoader(urlsWeb, this.getClass().getClassLoader());
			
			//Class clsWeb = clWeb.loadClass("com.example.modules.M2");
			
			//Method methodRegularClassLoader = clsWeb.getMethod("test");
			//methodRegularClassLoader.invoke(clsWeb.newInstance());

			Configuration config = new ConfigurationBuilder()
					.setUrls(ClasspathHelper.forClassLoader(clWeb)).addClassLoader(clWeb)
					.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.example.modules")))
					.setScanners(new SubTypesScanner(false), new ResourcesScanner(), new TypeAnnotationsScanner());

			Reflections reflections = new Reflections(config);

			Set<Class<? extends Object>> setModulos = reflections.getSubTypesOf(Object.class);

			for (Class<? extends Object> modulo : setModulos) {

				Method methodWeb = modulo.getMethod("test");
				methodWeb.invoke(modulo.newInstance());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
