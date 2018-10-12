package com.github.artemzi.hw24.figure;

import com.github.artemzi.hw24.Visitor;

public interface Shape {
    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);
}
