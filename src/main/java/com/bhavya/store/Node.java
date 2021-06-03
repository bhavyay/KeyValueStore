package com.bhavya.store;

public class Node {
  private Element element;
  private Node left;
  private Node right;
  private int size;

  public Node(Element element) {
    this.element = element;
    this.size = 1;
    left = null;
    right = null;
  }

  public Element getElement() {
    return element;
  }

  public Node getLeft() {
    return left;
  }

  public Node getRight() {
    return right;
  }

  public int getSize() {
    return size;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public void setRight(Node right) {
    this.right = right;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
