/*
 * Copyright 2015 Frank Hossfeld
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/*
 * Copyright 2014 Frank Hossfeld
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package de.gishmo.gwt.sema4g.example.rebind;

public class GeneratorUtils {
//
//	/**
//	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
//	 *
//	 * @param packageName The code package
//	 * @return The classes
//	 * @throws ClassNotFoundException
//	 * @throws java.io.IOException
//	 */
//	@SuppressWarnings("unchecked")
//	public static Class<ShowcaseWidget>[] getShowcaseWidgetClasses(String packageName)
//			throws ClassNotFoundException, IOException {
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		assert classLoader != null;
//		String path = packageName.replace('.', '/');
//		Enumeration<URL> resources = classLoader.getResources(path);
//		List<File> dirs = new ArrayList<File>();
//		while (resources.hasMoreElements()) {
//			URL resource = resources.nextElement();
//			dirs.add(new File(resource.getFile().replaceAll("%20", " ")));
//		}
//		ArrayList<Class<ShowcaseWidget>> classes = new ArrayList<Class<ShowcaseWidget>>();
//		for (File directory : dirs) {
//			classes.addAll(findShowcaseWidgetClasses(directory, packageName));
//		}
//		return classes.toArray(new Class[classes.size()]);
//	}
//
//	 /**
//	 * Recursive method used to find all classes in a given directory and subdirs.
//	 *
//	 * @param directory   The code directory
//	 * @param packageName The package name for classes found inside the code directory
//	 * @return The classes
//	 * @throws ClassNotFoundException
//	 */
//	@SuppressWarnings("unchecked")
//	private static List<Class<ShowcaseWidget>> findShowcaseWidgetClasses(File directory,
//																		 String packageName)
//		throws ClassNotFoundException {
//		List<Class<ShowcaseWidget>> classes = new ArrayList<Class<ShowcaseWidget>>();
//		if (!directory.exists()) {
//			return classes;
//		}
//		File[] files = directory.listFiles();
//		for (File file : files) {
//			if (file.isDirectory()) {
//				assert !file.getName().contains(".");
//				classes.addAll(findShowcaseWidgetClasses(file, packageName + "." + file.getName()));
//			} else if (file.getName().endsWith(".class")) {
//				classes.add((Class<ShowcaseWidget>) Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
//			}
//		}
//		return classes;
//	}
}
