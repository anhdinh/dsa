package com.dsa.controller;

import org.springframework.web.bind.annotation.*;
import tree.general.Heap;

import java.util.List;

@RestController
@RequestMapping("/api/heap")
public class HeapController {

    private final Heap heap;

    public HeapController(Heap heap) {
        this.heap = heap;
    }

    @PostMapping("/insert")
    public List<Integer> insert(@RequestParam("value") int value) {
        heap.insertHeap(value);
        return heap.toList();
    }

    @GetMapping
    public List<Integer> get() {
        return heap.toList();
    }

    @PostMapping("/reset")
    public List<Integer> reset() {
        heap.clear();
        return heap.toList();
    }
}
