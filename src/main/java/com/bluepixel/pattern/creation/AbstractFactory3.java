/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Win Tun Lin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.bluepixel.pattern.creation;

public class AbstractFactory3 {
	public static void main(String[] args) {
		new EducationMinistry(createJobSpecificUniversity());
	}

	private static UniversityFactory createJobSpecificUniversity() {

		String economy = "gdp";
		if(economy.equals("gdp")){
			return new TechnicalUniversity();
		} else {
			return new NationalUniversity();
		}

	}
}


interface UniversityFactory {
	public void hire(Teacher teacher);
	public void train(Student student);
}

class EducationMinistry{
	public EducationMinistry(UniversityFactory university) {
		university.hire(new ScienceTeacher());
		university.train(new ScienceStudent());
	}
}


class TechnicalUniversity implements UniversityFactory{

	@Override
	public void hire(Teacher teacher) {
		System.out.println("TechnicalUniversity : hire");
		teacher.beCertified();
	}

	@Override
	public void train(Student student) {
		System.out.println("TechnicalUniversity : train");
		student.receiveTraining();
	}
}

class NationalUniversity implements UniversityFactory{

	@Override
	public void hire(Teacher teacher) {
		System.out.println("NationalUniversity : hire");
		teacher.beCertified();
	}

	@Override
	public void train(Student student) {
		System.out.println("NationalUniversity : train");
		student.receiveTraining();
	}
	
}

interface Student {
	public void register();
	public void receiveTraining();
}

class ScienceStudent implements Student{

	@Override
	public void register() {	
		System.out.println("ScienceStudent : register");
	}
	@Override
	public void receiveTraining() {
		System.out.println("ScienceStudent : receiveTraining");
	}	
}

class ArtStudent implements Student{

	@Override
	public void register() {
		System.out.println("ArtStudent : register");		
	}

	@Override
	public void receiveTraining() {
		System.out.println("ArtStudent : receiveTraining");		
	}	
}

interface Teacher {
	public void beCertified();
	public void teach();
}

class ScienceTeacher implements Teacher{
	@Override
	public void beCertified() {
		System.out.println("ScienceTeacher : beCertified");
	}

	@Override
	public void teach() {
		System.out.println("ScienceTeacher : teach");
	}
}

class ArtTeacher implements Teacher {

	@Override
	public void beCertified() {
		System.out.println("ArtTeacher : beCertified");
	}

	@Override
	public void teach() {
		System.out.println("ArtTeacher : teach");
	}	
}













