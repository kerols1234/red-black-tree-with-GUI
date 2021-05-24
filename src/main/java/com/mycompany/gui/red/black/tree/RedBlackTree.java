/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.red.black.tree;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author kerols
 */
class Node {

    public int data;
    public Node parent;
    public Node left;
    public Node right;
    public int color; // 1 -> Red, 0 -> Black

    public Node(int data) {
        this.data = data;
        color = 1;
        left = null;
        right = null;
        parent = null;
    }

    public Node() {
        this.data = -1;
        color = 0;
        left = null;
        right = null;
        parent = null;
    }

}

public class RedBlackTree {

    Node root;
    Node lNode;
    JPanel jPanel;

    public RedBlackTree(JPanel x) {
        lNode = new Node();
        this.root = lNode;
        jPanel = x;
        x.setLayout(new GridBagLayout());
    }

    public void insert(int value) {
        Node node = new Node(value);
        node.left = lNode;
        node.right = lNode;
        node.parent = lNode;
        if (root == lNode) {
            root = node;
            node.color = 0;
        } else {
            Node temp = root, p = null;
            while (temp != lNode) {
                p = temp;
                if (temp.data > value) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
            node.parent = p;
            if (p.data > value) {
                p.left = node;
            } else {
                p.right = node;
            }
            while (node != root && p.color == 1) {
                if (p.parent.left == p) {
                    if (p.parent.right.color == 1) {
                        p.parent.right.color = 0;
                        p.parent.color = 1;
                        p.color = 0;
                        node = p.parent;
                        p = node.parent;
                        if (node == root) {
                            node.color = 0;
                        }
                    } else {
                        if (node == p.right) {
                            leftRotation(p);
                            rightRotation(node.parent);
                            node.color = 0;
                            node.right.color = 1;
                        } else {
                            rightRotation(p.parent);
                            p.color = 0;
                            p.right.color = 1;
                        }
                    }
                } else {
                    if (p.parent.left.color == 1) {
                        p.parent.left.color = 0;
                        p.color = 0;
                        p.parent.color = 1;
                        node = p.parent;
                        p = node.parent;
                        if (node == root) {
                            node.color = 0;
                        }
                    } else {
                        if (node == p.left) {
                            rightRotation(p);
                            leftRotation(node.parent);
                            node.color = 0;
                            node.left.color = 1;
                        } else {
                            leftRotation(p.parent);
                            p.color = 0;
                            p.left.color = 1;

                        }
                    }
                }
            }
        }
    }

    public void delete(int value) {
        Node temp = root, deleteNode = null;
        while (temp != lNode) {
            if (value == temp.data) {
                break;
            } else if (value < temp.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        if (temp == lNode || temp.data != value) {
            System.out.println("Tree doe not have this value " + value);
        } else {
            deleteNode = temp;
            while (true) {
                temp = replacementNode(deleteNode);
                deleteNode.data = temp.data;
                if (temp.left == lNode && temp.right == lNode) {
                    if (temp.color == 1) {
                        if (temp == root) {
                            root = lNode;
                        } else if (temp.parent.left == temp) {
                            temp.parent.left = lNode;
                        } else {
                            temp.parent.right = lNode;
                        }
                        break;
                    }
                    while (true) {
                        if (temp == root) { //case 1
                            root = lNode;
                            break;
                        } else if (temp.parent.right == temp) {
                            if (temp.parent.left.color == 1) { //case 2
                                if (temp.parent == root) {
                                    root = temp.parent.left;
                                }
                                temp.parent.color = 1;
                                temp.parent.left.color = 0;
                                rightRotation(temp.parent);
                            } else if (temp.parent.left.left.color == 1) { // case
                                if (temp.parent == root) {
                                    root = temp.parent.left;
                                }
                                temp.parent.left.color = temp.parent.color;
                                temp.parent.left.left.color = 0;
                                temp.parent.color = 0;
                                rightRotation(temp.parent);
                                temp.parent.right = lNode;
                                break;
                            } else if (temp.parent.left.right.color == 1) { //case 5
                                temp.parent.left.right.color = 0;
                                temp.parent.left.color = 1;
                                leftRotation(temp.parent.left);
                            } else if (temp.parent.color == 1) { // case 4
                                temp.parent.color = 0;
                                temp.parent.left.color = 1;
                                temp.parent.right = lNode;
                                break;
                            } else { //case 3
                                temp.parent.left.color = 1;
                                temp.parent.right = lNode;
                                break;
                            }
                        } else {
                            if (temp.parent.right.color == 1) { //case 2
                                if (temp.parent == root) {
                                    root = temp.parent.right;
                                }
                                temp.parent.color = 1;
                                temp.parent.right.color = 0;
                                leftRotation(temp.parent);
                            } else if (temp.parent.right.right.color == 1) { // case 6
                                if (temp.parent == root) {
                                    root = temp.parent.right;
                                }
                                temp.parent.right.color = temp.parent.color;
                                temp.parent.right.right.color = 0;
                                temp.parent.color = 0;
                                leftRotation(temp.parent);
                                temp.parent.left = lNode;
                                break;
                            } else if (temp.parent.right.left.color == 1) { //case 5
                                temp.parent.right.left.color = 0;
                                temp.parent.right.color = 1;
                                rightRotation(temp.parent.right);
                            } else if (temp.parent.color == 1) { // case 4
                                temp.parent.color = 0;
                                temp.parent.right.color = 1;
                                temp.parent.left = lNode;
                                break;
                            } else { //case 3
                                temp.parent.right.color = 1;
                                temp.parent.left = lNode;
                                break;
                            }
                        }
                    }
                    break;
                } else {
                    deleteNode = temp;
                }
            }
        }
    }

    public void clear() {
        jPanel.removeAll();
        jPanel.repaint();
        root = lNode;
    }

    public void printData() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != lNode) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public void print() {
        jPanel.paint(jPanel.getGraphics());
        printTree(root, 0, (jPanel.getWidth() / 2) - 19, jPanel.getWidth(), 20);
    }

    private void printTree(Node node, int l, int m, int r, int h) {
        if (node != lNode) {
            int x = 17;
            String s = String.valueOf(node.data);
            Graphics g = jPanel.getGraphics();
            if (String.valueOf(node.data).length() == 2) {
                x -= 3;
            } else if (s.length() > 2) {
                x -= 3+(4*(s.length()-2));
            }

            if (node.color == 0) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }
            g.fillOval(m, h, 38, 38);

            g.setColor(Color.WHITE);
            g.drawString(s, m + x, h + 24);

            g.setColor(Color.BLACK);
            Node p = node.parent;
            if (p.right == node) {
                g.drawLine(l + 38, h - 41, m + 19, h);
            } else if (node != root) {
                g.drawLine(r, h - 41, m + 19, h);
            }

            jPanel.paintComponents(g);

            printTree(node.left, l, (m - l) / 2 + l - 19, m, h + 60);
            printTree(node.right, m, (r - m) / 2 + m - 19, r, h + 60);
        }
    }

    private Node replacementNode(Node node) {
        if (node.left == lNode && node.right == lNode) {
            return node;
        } else {
            Node temp = node.left;
            if (temp != lNode) {
                while (temp.right != lNode) {
                    temp = temp.right;
                }
            }
            if ((temp == lNode || temp.color == 0) && temp.right != lNode) {
                temp = node.right;
                while (temp.left != lNode) {
                    temp = temp.left;
                }
            }
            return temp;
        }
    }

    private void leftRotation(Node node) {
        Node leftChild = node.right;
        node.right = leftChild.left;
        if (leftChild.left != lNode) {
            leftChild.left.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == lNode) {
            this.root = leftChild;
        } else if (node == node.parent.left) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }
        leftChild.left = node;
        node.parent = leftChild;
    }

    private void rightRotation(Node node) {
        Node rightChild = node.left;
        node.left = rightChild.right;
        if (rightChild.right != lNode) {
            rightChild.right.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == lNode) {
            this.root = rightChild;
        } else if (node == node.parent.right) {
            node.parent.right = rightChild;
        } else {
            node.parent.left = rightChild;
        }
        rightChild.right = node;
        node.parent = rightChild;
    }
}
