import { Router, UrlTree } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard {
  constructor(private auth: AuthService, private router: Router) { }
  canActivate(): boolean | Observable<boolean | UrlTree> {
    this.auth.isLoggedIn.subscribe(res => {
      if (!res) {
        this.router.navigate(['/login']);
      }
    });
    return this.auth.isLoggedIn;
  }
}