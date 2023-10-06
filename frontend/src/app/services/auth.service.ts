import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, combineLatest, map, tap } from 'rxjs';
import { User } from '../models/user';
import { Role } from '../models/role';
import { LoginForm } from '../models/login-form';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authUrl = 'http://localhost:8080/api/auth';
  private _isLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private _publicUser: User = {
    username: 'anonymous',
    token: '',
    role: Role.VIEWER,
  }
  private _user: BehaviorSubject<User> = new BehaviorSubject<User>(this._publicUser);

  constructor(private http: HttpClient, private router: Router) {
    this._isLoggedIn.next(!!localStorage.getItem('token'));
    this.fetchUserFromToken();
  }

  private mapRole(role: string): Role {
    switch (role) {
      case 'ROLE_ADMIN':
        return Role.ADMIN;
      case 'ROLE_USER':
        return Role.USER;
      default:
        return Role.VIEWER;
    }
  }

  private fetchUserFromToken() {
    if (this._isLoggedIn.value) {
      const token = localStorage.getItem('token');
      const payload = token?.split('.')[1];
      const decodedPayload = JSON.parse(atob(payload as string));
      const user: User = {
        username: decodedPayload.sub,
        token: token as string,
        role: this.mapRole(decodedPayload.scope),
      }
      this._user.next(user);
    } else {
      this._user.next({
        username: 'anonymous',
        token: '',
        role: Role.VIEWER,
      })
    }
  }

  get user(): Observable<User> {
    return this._user.asObservable();
  }

  login(userCred: LoginForm): Observable<User> {
    return this.http.post<User>(`${this.authUrl}/token`, userCred).pipe(
      tap(res => {
        localStorage.setItem('token', res.token);
        this._isLoggedIn.next(true);
        this.fetchUserFromToken();
        this.router.navigate(['/home']);
      })
    );
  }

  get token(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
    this._isLoggedIn.next(false);
    this._user.next(this._publicUser);
  }

  get isLoggedIn(): Observable<boolean> {
    return combineLatest([
      this._isLoggedIn.asObservable(),
      this.isTokenValid(),
    ]).pipe(
      map(([isLoggedIn,isTokenValid]) => isLoggedIn && isTokenValid)
    );
  }

  //checks not expired
  isTokenValid(): Observable<boolean> {
    return this._user.pipe(
      map(user => {
        if (!user || !user.token) return false
        const payload = user.token?.split('.')[1];
        const decodedPayload = JSON.parse(atob(payload as string));
        const exp = decodedPayload.exp;
        return Date.now() <= exp * 1000;
      })
    )
  }

}
