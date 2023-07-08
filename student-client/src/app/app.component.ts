import { environment } from './../environments/environment.prod';
import { AnalyticsService } from './AnalyticsService';
import { Router, NavigationEnd } from '@angular/router';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Student Register';
  showhideNewBtn = true;

  currentUrl = '';
  constructor(
    private router: Router,
    private analyticsService: AnalyticsService
  ) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.currentUrl = event.url;
        console.log('NavigationEnd:', event);
        console.log('this.currentUrl:', this.currentUrl);
        if ('/create' === this.currentUrl) {
          this.showhideNewBtn = false;
        } else {
          this.showhideNewBtn = true;
        }
      }
    });
    if (environment.production) {
      this.analyticsService.initAdobeLaunchDataLayer();
      this.analyticsService.injectAdobeLaunchScript();
    }
  }

  ngOnInit() {
    // if(this.router.url == '/stuent'){

    // }
    console.log('router', this.router.url);

    // this.router.events.subscribe((navEnd: NavigationEnd) => {
    //   this.currentUrl = navEnd.url;
    //   console.log(this.currentUrl);
    // });
  }

  displayStyle = 'none';

  openPopup() {
    this.displayStyle = 'block';
  }
  closePopup() {
    this.displayStyle = 'none';
  }
}
