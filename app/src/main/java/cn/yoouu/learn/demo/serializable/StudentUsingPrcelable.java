package cn.yoouu.learn.demo.serializable;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class StudentUsingPrcelable implements Parcelable {
  private String name;
  private int age;
  private ScoreUsingParcelable score;

  @Override
  public String toString() {
    return "StudentUsingPrcelable{" +
      "name='" + name + '\'' +
      ", age=" + age +
      ", score=" + score +
      '}';
  }

  public StudentUsingPrcelable(String name, int age, ScoreUsingParcelable score) {
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

  public ScoreUsingParcelable getScore() {
    return score;
  }

  public void setScore(ScoreUsingParcelable score) {
    this.score = score;
  }

  protected StudentUsingPrcelable(Parcel in) {
    name = in.readString();
    age = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeInt(age);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<StudentUsingPrcelable> CREATOR = new Creator<StudentUsingPrcelable>() {
    @Override
    public StudentUsingPrcelable createFromParcel(Parcel in) {
      return new StudentUsingPrcelable(in);
    }

    @Override
    public StudentUsingPrcelable[] newArray(int size) {
      return new StudentUsingPrcelable[size];
    }
  };
}


class ScoreUsingParcelable implements Parcelable {
  private static final long serialVersionUID = -6445390601508718032L;
  private int math;
  private int english;
  private int chinese;
  private String grade;

  @Override
  public String toString() {
    return "ScoreUsingParcelable{" +
      "math=" + math +
      ", english=" + english +
      ", chinese=" + chinese +
      ", grade='" + grade + '\'' +
      '}';
  }

  public ScoreUsingParcelable(int math, int english, int chinese) {
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

  public void setGrade(String grade) {
    this.grade = grade;
  }

  protected ScoreUsingParcelable(Parcel in) {
    math = in.readInt();
    english = in.readInt();
    chinese = in.readInt();
    grade = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(math);
    dest.writeInt(english);
    dest.writeInt(chinese);
    dest.writeString(grade);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ScoreUsingParcelable> CREATOR = new Creator<ScoreUsingParcelable>() {
    @Override
    public ScoreUsingParcelable createFromParcel(Parcel in) {
      return new ScoreUsingParcelable(in);
    }

    @Override
    public ScoreUsingParcelable[] newArray(int size) {
      return new ScoreUsingParcelable[size];
    }
  };
}

