/*import { Component } from '@angular/core';
import {ApiKeyService} from "./keys.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'jhi-keys',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './keys.component.html',
  styleUrl: './keys.component.scss'
})
export class KeysComponent {

  apiKeyId: string = '';
  apiKeySecret: string = '';

  constructor(private apiKeyService: ApiKeyService) { }

  submitApiKey() {
    this.apiKeyService.sendApiKeys(this.apiKeyId, this.apiKeySecret).subscribe(
        response => {
          console.log('API keys sent successfully:', response);
          // You can add further actions here, e.g., show a success message
        },
        error => {
          console.error('Error sending API keys:', error);
          // Handle errors accordingly
        }
    );
  }
}

 */
