package com.bhavya.store;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class LSMTree {
  private Node tree;

  private final ReentrantLock inMemoryAccessLock;
  private final BinaryTree binaryTree;

  public LSMTree() {
    tree = null;
    inMemoryAccessLock = new ReentrantLock();
    binaryTree = new BinaryTree();
  }

  public void put(String key, String value) {
    inMemoryAccessLock.lock();
    try {
      binaryTree.upsert(tree, new Element(key, value));
    } finally {
      inMemoryAccessLock.unlock();
    }
  }

  public String get(String key) {
    inMemoryAccessLock.lock();
    Element data;
    try {
      data = binaryTree.find(tree, key);
      if (Objects.isNull(data)) {
        System.out.println("Element with key " + key + " is not found in memory");
      }
      if (Objects.nonNull(data)) {
        return data.getValue();
      }
    } finally {
      inMemoryAccessLock.unlock();
    }
    return null;
  }
}
