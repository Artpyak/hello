package com.banana.injection.factory;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ConfigProcessor {

    @Getter
    private Map<String, String> propertieMap;

    @SneakyThrows
    public void init(String fileName) {
        FileReader in = new FileReader("src/main/resources/" + fileName);
        Stream<String> lines = new BufferedReader(in).lines();
        propertieMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }
}
