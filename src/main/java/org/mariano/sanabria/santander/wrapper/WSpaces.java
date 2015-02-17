package org.mariano.sanabria.santander.wrapper;

import java.util.Arrays;
import java.util.List;

public class WSpaces extends WResponse{
	List<String> separated;

	public WSpaces(String result) {
		separated = Arrays.asList(splitBySpaces(result));
	}
}
