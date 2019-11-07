export class Income {
    public id: number;
    public clientType: string;
    public clientId: number;
    public name: string;
    public date: string;
    public description: string;
    public amount: number;

    public constructor (id?: number, clientType?: string, clientId?: number,
                        name?: string, date?: string, description?: string, amount?: number ){
            this.id = id;
            this.clientType = clientType;
            this.clientId = clientId;
            this.name = name;
            this.date = date;
            this.description = description;
            this.amount = amount;    
                            
    }
}