package cn.yoouu.learn.demo.serializable;

import java.io.Serializable;

// 实现序列化接口
public class StudentUsingJava implements Serializable {
  private static final long serialVersionUID = 3756177011742159026L;
  /**
   * 加上 transient 不会被序列化
   */
//  private transient String name;
  private String name;
  private int age;
  private ScoreUsingJava score;


  public StudentUsingJava(String name, int age, ScoreUsingJava score) {
    this.name = name;
    this.age = age;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public ScoreUsingJava getScore() {
    return score;
  }

  public void setScore(ScoreUsingJava score) {
    this.score = score;
  }
}

class ScoreUsingJava implements Serializable{
  private static final long serialVersionUID = -6445390601508718032L;
  private int math;
  private int english;
  private int chinese;
  private String grade;

  public ScoreUsingJava(int math, int english, int chinese) {
    this.math = math;
    this.english = english;
    this.chinese = chinese;
    if (math > 90 && english > 90 && chinese > 90) {
      this.grade = "A";
    } else if (math > 80 && english > 80 && chinese > 80) {
      this.grade = "B";
    } else {
      this.grade = "C";
    }
  }

  public int getMath() {
    return math;
  }

  public void setMath(int math) {
    this.math = math;
  }

  public int getEnglish() {
    return english;
  }

  public void setEnglish(int english) {
    this.english = english;
  }

  public int getChinese() {
    return chinese;
  }

  public void setChinese(int chinese) {
    this.chinese = chinese;
  }

  public String getGrade() {
    return grade;
  }
}
