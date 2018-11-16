import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';
//import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { Component, OnInit,OnDestroy } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { map } from "rxjs/operators";
import {Response} from '@angular/http';
import { RestaurantService } from '../restaurant.service';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit,OnDestroy {
 
  dishes:any;
  dtTrigger:Subject<any> = new Subject();
  dtOptions: DataTables.Settings = {};

 constructor(private http: HttpClient,private restaurantService: RestaurantService) { }
 
 dishes$: Observable<any>;
 private searchTerms = new Subject<string>();
// Push a search term into the observable stream.
  search(dishName: string): void {
    this.searchTerms.next(dishName);
  }

  ngOnInit(): void {
    this.dishes$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((dishName: string) => this.restaurantService.findByDishName(dishName)

      ),
    );
    console.log('Dishes are fetched');

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };


  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }


private extractData(res: Response) {
    const body = res.json();
    return body.data || {};
  }
  

}
