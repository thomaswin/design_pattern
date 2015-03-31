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

class Soldier {
	
	private String physicalTraining;
	private String technicalTraining;
	private boolean isPassed;
	
	private Soldier(){
		
	}
	public static Builder createBuilder(){
		return new Builder();
	}	
	
	public String getPhysicalTraining() {
		return physicalTraining;
	}
	public void setPhysicalTraining(String physicalTraining) {
		this.physicalTraining = physicalTraining;
	}
	public String getTechnicalTraining() {
		return technicalTraining;
	}
	public void setTechnicalTraining(String technicalTraining) {
		this.technicalTraining = technicalTraining;
	}
	public boolean isPassed() {
		return isPassed;
	}
	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}



	@Override
	public String toString() {
		return "Soldier [physicalTraining=" + physicalTraining
				+ ", technicalTraining=" + technicalTraining + ", isPassed="
				+ isPassed + "]";
	}

	public static class Builder {
		private final Soldier soldier = new Soldier();
		private boolean done;
		
		private Builder(){
			
		}
		public Soldier build(){
			done = true;
			return soldier;
		}
		public Builder setPhysicalTraining(String physical){
			check();
			soldier.physicalTraining = physical;
			return this;
		}
		
		public Builder setTechnicalTraining(String technical){
			check();
			soldier.technicalTraining = technical;
			return this;
		}
		public Builder testTraining(){
			check();
			soldier.isPassed = true;
			return this;
		}
		
		private void check(){
			if(done){
				throw new IllegalArgumentException("not complete yet");
			}
		}
	}
}

public class Builder2 {
	public static void main(String[] args) {
		Soldier armySoldier = Soldier.createBuilder()
				.setPhysicalTraining("200_hr_desert")
				.setTechnicalTraining("navy_combat")
				.testTraining()
				.build();
		Soldier armySoldier1 = Soldier.createBuilder()
				.setPhysicalTraining("200_hr_desert")
				.setTechnicalTraining("navy_combat")				
				.build();
		System.out.println("armySoldier : " + armySoldier);
		System.out.println("armySoldier : " + armySoldier1);
	}
}
