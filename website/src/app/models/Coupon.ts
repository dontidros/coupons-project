

export class Coupon {
    public id: number;
    public companyId: number;
    public category: string;
    public title: string;
    public description: string;
    public startDate: string;
    public endDate: string;
    public amount: number;
    public price: number;
    public image: string;

    public constructor(id?: number, companyId?: number, category?: string,
         title?: string, description?: string, startDate?: string, endDate?: string,
          amount?: number, price?: number, image?: string){

            this.id = id;
            this.companyId = companyId;
            this.category = category;
            this.title = title;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
            this.amount = amount;
            this.price = price;
            this.image = image;

    }
}