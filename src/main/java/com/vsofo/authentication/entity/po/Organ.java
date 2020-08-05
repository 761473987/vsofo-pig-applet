package com.vsofo.authentication.entity.po;

import java.io.Serializable;

/**
 * @author lituo
 * @version 1.0
 * @date 2020/6/1 11:21
 * @description 区域分类
 */
public class Organ implements Serializable {

   private long organID;
   private long dealerID;
   private String organName;

   @Override
   public String toString() {
      return "Organ{" +
              "organID=" + organID +
              ", dealerID=" + dealerID +
              ", organName='" + organName + '\'' +
              '}';
   }

   public Organ(long organID, long dealerID, String organName) {
      this.organID = organID;
      this.dealerID = dealerID;
      this.organName = organName;
   }

   public Organ() {
   }

   public long getOrganID() {
      return organID;
   }

   public void setOrganID(long organID) {
      this.organID = organID;
   }

   public long getDealerID() {
      return dealerID;
   }

   public void setDealerID(long dealerID) {
      this.dealerID = dealerID;
   }

   public String getOrganName() {
      return organName;
   }

   public void setOrganName(String organName) {
      this.organName = organName;
   }
}
