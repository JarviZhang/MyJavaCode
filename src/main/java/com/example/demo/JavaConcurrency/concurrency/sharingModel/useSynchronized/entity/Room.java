package com.example.demo.JavaConcurrency.concurrency.sharingModel.useSynchronized.entity;

public class Room {
    int count = 0;
    public void increment(){
        synchronized (this){
            count++;
        }
    }

    public void decreatment(){
        synchronized (this){
            count--;
        }
    }

    public int get(){
        synchronized (this){
            return count;
        }
    }
}
