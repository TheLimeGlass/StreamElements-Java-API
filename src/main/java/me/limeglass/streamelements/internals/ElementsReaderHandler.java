package me.limeglass.streamelements.internals;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reflections.Reflections;

import com.google.gson.stream.JsonReader;

public abstract class ElementsReaderHandler {

	@SuppressWarnings("rawtypes")
	protected static Map<Class<? extends ElementsReader>, ElementsReader> readers = new HashMap<Class<? extends ElementsReader>, ElementsReader>();
	
	/**
	 * Registers an ElementsReader to read a JsonReader and format it into a response.
	 * 
	 * @param reader The class of the ElementsReader.
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static <T extends ElementsReader> void registerReader(Class<? extends ElementsReader> clazz) {
		Class<T> reader = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
		if (!readers.containsKey(clazz))
			try {
				readers.put(clazz, reader.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {}
	}
	
	/**
	 * Load any ElementReaders found at the defined package path.
	 * 
	 * @param packagePath The package path to load from. Any class of an ElementsReader will get loaded.
	 */
	public static void load(String packagePath) {
		Reflections reflections = new Reflections(packagePath);
		@SuppressWarnings("rawtypes")
		Set<Class<? extends ElementsReader>> classes = reflections.getSubTypesOf(ElementsReader.class);
		classes.forEach(reader -> registerReader(reader));
		//Reflection.initialize(classes.toArray(new Class[classes.size()]));
	}
	
	/**
	 * Finds any registered ElementsReaders that match the prediction.
	 * 
	 * @param predicted The predicted ElementsResponse return.
	 * 
	 * @return Any ElementReaders that return with the offering predicted ElementsResponse.
	 */
	public static <T extends ElementsResponse> List<ElementsReader<T>> findPredicted(Class<T> predicted) {
		return readers.entrySet().stream()
				.filter(predict -> predict.getKey().equals(predicted))
				.map(predict -> predict.getValue())
				.collect(Collectors.toList());
	}
	
	/**
	 * Reads and returns the response of any registered ElementsReaders that match the prediction.
	 * 
	 * @param reader The JsonReader to read from.
	 * @param predicted The predicted ElementsResponse return.
	 * 
	 * @return Any ElementReaders that return with the offering predicted ElementsResponse.
	 */
	public static <T extends ElementsResponse> List<ElementsOptional<T>> readPredicted(JsonReader reader, Class<T> predicted) {
		return readers.entrySet().stream()
				.filter(predict -> predict.getKey().equals(predicted))
				.map(predict -> predict.getValue().read(reader))
				.collect(Collectors.toList());
	}
	
	/**
	 * Reads and returns the response of any registered ElementsReaders that match the prediction as a Stream.
	 * 
	 * @param reader The JsonReader to read from.
	 * @param predicted The predicted ElementsResponse return.
	 * 
	 * @return Any ElementReaders that return with the offering class ElementsResponse. As a stream, the mapped return is the ElementsResponse.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ElementsResponse> Stream<ElementsOptional<T>> streamPredicted(JsonReader reader, Class<T> predicted) {
		return readers.entrySet().stream()
				.filter(predict -> predict.getKey().equals(predicted))
				.map(predict -> predict.getValue().read(reader));
	}
	
	/**
	 * @return Returns all the classes of the ElementsReader currently registered.
	 */
	@SuppressWarnings("rawtypes")
	public static Set<Class<? extends ElementsReader>> getReaderClasses() {
		return readers.keySet();
	}
	
	/**
	 * @return Returns the entry set of the registered readers.
	 */
	@SuppressWarnings("rawtypes")
	public static Set<Entry<Class<? extends ElementsReader>, ElementsReader>> getEntryReaders() {
		return readers.entrySet();
	}

}
