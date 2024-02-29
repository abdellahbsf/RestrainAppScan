import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ScansService } from '../service/scans.service';
import { NgForOf, NgIf } from '@angular/common';
import { delay } from 'rxjs';

@Component({
  selector: 'jhi-scans',
  standalone: true,
  imports: [NgIf, NgForOf],
  templateUrl: './scans.component.html',
  styleUrl: './scans.component.scss',
})
export class ScansComponent implements OnInit {
  allApplications: any;
  applicationDetails: any;
  applicationScans: any;
  applicationIssues: any;

  constructor(
    private scansService: ScansService,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit() {
    // Fetch all Applications
    this.scansService.getAllApplications().subscribe(
      data => {
        this.allApplications = data;
        //this.cdr.detectChanges();
      },
      //error => {
      //console.error('Failed to fetch Applications:', error);
      //},
    );

    // Fetch all Applications
    this.scansService.getApplicationDetails().subscribe(
      data => {
        this.applicationDetails = data;
        this.cdr.detectChanges();
      },
      //error => {
      //console.error('Failed to fetch Application details:', error);
      //},
    );
    // Fetch all scans
    this.scansService.getApplicationScans().subscribe(
      data => {
        this.applicationScans = data;
        this.cdr.detectChanges();
      },
      error => {
        console.error('Failed to fetch application scans:', error);
      },
    );

    // Fetch all issues with a delay of 3 seconds
    this.scansService
      .getApplicationIssues()
      .pipe(
        delay(3000), // 2000 milliseconds = 3 seconds
      )
      .subscribe(
        data => {
          this.applicationIssues = data;
          this.cdr.detectChanges();
        },
        error => {
          console.error('Failed to fetch application issues:', error);
        },
      );
    /*
    // Fetch all issues
    this.scansService.getApplicationIssues().subscribe(
      data => {
        this.applicationIssues = data;
        this.cdr.detectChanges();
      },
      error => {
        console.error('Failed to fetch application issues:', error);
      },
    );

 */
  }
}
