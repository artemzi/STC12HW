package com.github.artemzi.hw24;

import com.github.artemzi.hw24.figure.Circle;
import com.github.artemzi.hw24.figure.CompoundShape;
import com.github.artemzi.hw24.figure.Dot;
import com.github.artemzi.hw24.figure.Rectangle;

public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}
