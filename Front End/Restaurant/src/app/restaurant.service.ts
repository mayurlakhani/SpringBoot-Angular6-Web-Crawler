import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject, pipe } from 'rxjs';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({'Content-type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private http: HttpClient) { }
  private restaurantUrl='rest/restaurant';

  getRestaurants() {
    return this.http.get('rest/restaurant/allRestaurants');
  }
getDishes() {
    return this.http.get('rest/restaurant/alldishDetails');
  }
   
  getCategoryByRest(id: number): Observable<any> {
    const url = `${this.restaurantUrl}/allCategories/${id}`;
    return this.http.get(url).pipe(
      tap(_ => 
      catchError(this.handleError(`category by RestId=${id}`))
    ));
  }

 getDishesByCategoryId(id: number): Observable<any> {
    const url = `${this.restaurantUrl}/allMenues/${id}`;
    return this.http.get(url).pipe(
      tap(_ => 
      catchError(this.handleError(`category by RestId=${id}`))
    ));
  }

  findByDishName(dishName: string): Observable<any> {
   const url = `rest/restaurant/findByDishName/${dishName}`;
    if (!dishName.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get(url).pipe(
      catchError(this.handleError('findByDishName', []))
    );
  }

   private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

   

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
