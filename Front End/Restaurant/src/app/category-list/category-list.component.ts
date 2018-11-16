import { Component, OnInit,OnDestroy } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Params } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { map } from "rxjs/operators";
import {RestaurantDetails} from '../restaurantDetails';
import {Response} from '@angular/http';
import { RestaurantService } from '../restaurant.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {
  title="CategoryList"
	categories:any;
	dtTrigger:Subject<any> = new Subject();
	dtOptions: DataTables.Settings = {};
  
   constructor( private route: ActivatedRoute,
    private location: Location,
    private http: HttpClient, 
    private  restaurantService:RestaurantService) { }


   ngOnInit() {
  console.log('Categories are fetched');

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };

    const id = +this.route.snapshot.paramMap.get('id');
    this.restaurantService.getCategoryByRest(id).subscribe(
        categories => { this.categories = categories; 
        this.dtTrigger.next();
    });
  }

 	ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  	}


	private extractData(res: Response) {
    const body = res.json();
    return body.data || {};
  	}

	getCategoryByRestaurant(): void {
	    const id = +this.route.snapshot.paramMap.get('id');
	    this.restaurantService.getCategoryByRest(id)
	      .subscribe(categories => this.categories = categories);
	}

	goBack(): void {
	    this.location.back();
    }

}
