package com.bhavya.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinaryTree {

  public Node createTree(List<Element> sortedElements) {
    if (sortedElements.isEmpty()) {
      return null;
    }
    int size = sortedElements.size();

    Node root = new Node(sortedElements.get(size/2));
    root.setLeft(createTree(sortedElements.subList(0, size/2)));
    root.setSize(size);

    int rightIndex = size/2 + 1;
    if (rightIndex < size) {
      root.setRight(createTree(sortedElements.subList(rightIndex, size)));
    }
    return root;
  }

  public void upsert(Node tree, Element element) {
    if (Objects.isNull(tree)) {
      tree = new Node(element);
    } else if (tree.getElement().getKey().compareTo(element.getKey()) > 0) {
      upsert(tree.getRight(), element);
      tree.setSize(tree.getSize() + 1);
    } else if (tree.getElement().getKey().compareTo(element.getKey()) < 0) {
      upsert(tree.getLeft(), element);
      tree.setSize(tree.getSize() + 1);
    } else {
      tree.getElement().setValue(element.getValue());
    }
  }

  public Element find(Node tree, String key) {
    if (Objects.isNull(tree)) {
      return null;
    } else if (tree.getElement().getKey().equals(key)) {
      return tree.getElement();
    } else if (tree.getElement().getKey().compareTo(key) > 0) {
      return find(tree.getLeft(), key);
    } else {
      return find(tree.getRight(), key);
    }
  }

  public List<Element> traverse(Node tree) {
    List<Element> elements = new ArrayList<>();
    if (Objects.nonNull(tree)) {
      elements.addAll(traverse(tree.getLeft()));
      elements.add(tree.getElement());
      elements.addAll(traverse(tree.getRight()));
    }
    return elements;
  }
}
