

export class LoginFields {
    public email: string;
    public password: string;

    public constructor(email?: string, password?: string) {
        this.email = email;
        this.password = password;
    }
}