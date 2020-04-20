package com.zjj.DataStructures.linkedlist;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

    }
}


class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {

        }
    }
}


class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode heroNode;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}