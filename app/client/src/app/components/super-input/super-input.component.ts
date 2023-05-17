import {AfterViewInit, Component, ElementRef, Inject, Input, ViewChild} from '@angular/core';
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'app-super-input',
  templateUrl: './super-input.component.html',
  styleUrls: ['./super-input.component.scss']
})
export class SuperInputComponent implements AfterViewInit {

  @ViewChild('myDiv') divElement!: ElementRef;

  @Input() text!: string;

  constructor(@Inject(DOCUMENT) private document: Document) { }

  ngAfterViewInit() {
    const node = this.divElement.nativeElement as HTMLElement;
    const parent = node.parentElement as HTMLElement;

    const x = this.document.createTextNode(node.textContent!);

    const newNode = this.replaceTags(x);

    node.remove();

    parent.appendChild(newNode);
  }

  private replaceTags(text: Text): HTMLElement | Text {

    const l1 = text.textContent!.split("<");
    if (l1.length < 2 ) return text;
    const l2 = l1[1].split(">");
    if (l2.length < 2) return text;

    if (l2[0] == 'script') {
      const x = text.textContent!.split("<script>");
      const y = x[1].split("</script>");
      const elem = this.document.createElement('script');
      const text2 = this.document.createTextNode(y[0]);
      elem.appendChild(text2);
      return elem;
    }
    return text;
  }

}
