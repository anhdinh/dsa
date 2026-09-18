package com.dsa.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OomService {

    private final List<byte[]> holder = new ArrayList<>();

    public void trigger() {
        int count = 0;
        while (true) {
            holder.add(new byte[1024 * 1024]);
            count++;
        }
    }
}
