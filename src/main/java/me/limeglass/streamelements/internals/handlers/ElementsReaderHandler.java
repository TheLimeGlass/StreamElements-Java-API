package me.limeglass.streamelements.internals.handlers;

import java.io.IOException;
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

import me.limeglass.streamelements.internals.ElementsOptional;

public abstract class ElementsReaderHandler {

	protected static Map<ElementsReader<?>, Class<ElementsResponse>> readers = new HashMap<ElementsReader<?>, Class<ElementsResponse>>();
	
	/**
	 * Registers an ElementsReader to read a JsonReader and format it into a response.
	 * 
	 * @param reader The class of the ElementsReader.
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static <T extends ElementsReader> void registerReader(Class<? extends ElementsReader> clazz) {
		Class<ElementsResponse> response = (Class<ElementsResponse>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
		if (readers.keySet().parallelStream().allMatch(reader -> !reader.getClass().equals(clazz))) {
			try {
				readers.put(clazz.newInstance(), response);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
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
	}
	
	/**
	 * Finds any registered ElementsReaders that match the prediction.
	 * 
	 * @param predicted The predicted ElementsResponse return.
	 * 
	 * @return Any ElementReaders that return with the offering predicted ElementsResponse.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ElementsResponse> List<ElementsReader<T>> findPredicted(Class<T> response) {
		return readers.entrySet().stream()
				.filter(predict -> predict.getValue().equals(response))
				.map(predict -> (ElementsReader<T>) predict.getKey())
				.collect(Collectors.toList());
	}
	
	/**
	 * Reads and returns the response of any registered ElementsReaders that match the prediction.
	 * 
	 * @param reader The JsonReader to read from.
	 * @param predicted The predicted ElementsResponse return.
	 * 
	 * @return Any ElementReaders that return with the offering predicted ElementsResponse.
	 * @throws IOException 
	 */
	public static <T extends ElementsResponse> List<ElementsOptional<T>> readPredicted(JsonReader reader, Class<T> response) throws IOException {
		List<ElementsOptional<T>> stream = findPredicted(response).stream()
				.map(predict -> (ElementsOptional<T>) predict.read(reader))
				.collect(Collectors.toList());
		for (ElementsOptional<T> optional : stream) {
			if (optional.hasError())
				throw optional.getException();
		}
		return stream;
	}
	
	/**
	 * Reads and returns the response of any registered ElementsReaders that match the prediction as a Stream.
	 * 
	 * @param reader The JsonReader to read from.
	 * @param predicted The predicted ElementsResponse return.
	 * 
	 * @return Any ElementReaders that return with the offering class ElementsResponse. As a stream, the mapped return is the ElementsResponse.
	 * @throws IOException 
	 */
	public static <T extends ElementsResponse> Stream<ElementsOptional<T>> streamPredicted(JsonReader reader, Class<T> predicted) throws IOException {
		return readPredicted(reader, predicted).stream();
	}
	
	/**
	 * @return Returns all the classes of the ElementsReader currently registered.
	 */
	public static Set<ElementsReader<?>> getReaders() {
		return readers.keySet();
	}
	
	/**
	 * @return Returns the entry set of the registered readers.
	 */
	public static Set<Entry<ElementsReader<?>, Class<ElementsResponse>>> getEntryReaders() {
		return readers.entrySet();
	}

}
