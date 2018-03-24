package com.hawksoft.platform.entity;

import java.io.Serializable;

public class UnmannedBoat implements Serializable {
    private int id;//key,自增
    private String number;//无人船编号
    private double course;//航向
    private double originalSpeed;//原始速度
    private double trailAngle;//航迹角
    private double manualCourse;//手动给定航向
    private double autoCourse;//自动给定航向
    private double spacing;//待行距
    private double yawDistance;//偏航距
    private double leftOutput;//左电机输出
    private double rightOutput;//右电机输出
    private double accelerator;//油门控制量
    private double courseChangeRate;//航向变化率
    private double yawChangeRate;//偏航距变化率
    private double sailingSpeed;//航行速度
    private double targetSpeed;//目标速度
    private double longitude;//经度
    private double latitude;//纬度
    private double obstacleDistance;//障碍距离
    private double obstacleAngle;//障碍角度
    private String time;//时间

    public UnmannedBoat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public double getOriginalSpeed() {
        return originalSpeed;
    }

    public void setOriginalSpeed(double originalSpeed) {
        this.originalSpeed = originalSpeed;
    }

    public double getManualCourse() {
        return manualCourse;
    }

    public void setManualCourse(double manualCourse) {
        this.manualCourse = manualCourse;
    }

    public double getAutoCourse() {
        return autoCourse;
    }

    public void setAutoCourse(double autoCourse) {
        this.autoCourse = autoCourse;
    }

    public double getSpacing() {
        return spacing;
    }

    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }

    public double getYawDistance() {
        return yawDistance;
    }

    public void setYawDistance(double yawDistance) {
        this.yawDistance = yawDistance;
    }

    public double getLeftOutput() {
        return leftOutput;
    }

    public void setLeftOutput(double leftOutput) {
        this.leftOutput = leftOutput;
    }

    public double getRightOutput() {
        return rightOutput;
    }

    public void setRightOutput(double rightOutput) {
        this.rightOutput = rightOutput;
    }

    public double getAccelerator() {
        return accelerator;
    }

    public void setAccelerator(double accelerator) {
        this.accelerator = accelerator;
    }

    public double getCourseChangeRate() {
        return courseChangeRate;
    }

    public void setCourseChangeRate(double courseChangeRate) {
        this.courseChangeRate = courseChangeRate;
    }

    public double getSailingSpeed() {
        return sailingSpeed;
    }

    public void setSailingSpeed(double sailingSpeed) {
        this.sailingSpeed = sailingSpeed;
    }

    public double getTargetSpeed() {
        return targetSpeed;
    }

    public void setTargetSpeed(double targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getObstacleDistance() {
        return obstacleDistance;
    }

    public void setObstacleDistance(double obstacleDistance) {
        this.obstacleDistance = obstacleDistance;
    }

    public double getObstacleAngle() {
        return obstacleAngle;
    }

    public void setObstacleAngle(double obstacleAngle) {
        this.obstacleAngle = obstacleAngle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTrailAngle() {
        return trailAngle;
    }

    public void setTrailAngle(double trailAngle) {
        this.trailAngle = trailAngle;
    }

    public double getYawChangeRate() {
        return yawChangeRate;
    }

    public void setYawChangeRate(double yawChangeRate) {
        this.yawChangeRate = yawChangeRate;
    }

    @Override
    public String toString() {
        return "WaterQuality{" +
                "id=" + id +
                ", number=" + number +
                ", course='" + course + '\'' +
                ", originalSpeed=" + originalSpeed +
                ", trailAngle=" + trailAngle +
                ", manualCourse=" + manualCourse +
                ", autoCourse=" + autoCourse +
                ", spacing=" + spacing +
                ", yawDistance=" + yawDistance +
                ", leftOutput=" + leftOutput +
                ", rightOutput='" + rightOutput +
                ", accelerator='" + accelerator +
                ", courseChangeRate='" + courseChangeRate +
                ", yawChangeRate='" + yawChangeRate +
                ", sailingSpeed='" + sailingSpeed +
                ", targetSpeed=" + targetSpeed +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", obstacleDistance=" + obstacleDistance +
                ", obstacleAngle=" + obstacleAngle +
                ", time=" + time +
                '}';

    }

}
