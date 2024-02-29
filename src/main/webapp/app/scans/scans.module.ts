import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ScansRoutingModule } from './scans-routing.module';
import { ScansService } from '../service/scans.service';
import { ScansComponent } from './scans.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [],
  imports: [CommonModule, ScansRoutingModule, BrowserModule, HttpClientModule, ScansComponent],
  providers: [ScansService],
  bootstrap: [],
})
export class ScansModule {}
