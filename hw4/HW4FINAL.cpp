// Hw4.cpp : This file contains the 'main' function. Program execution begins and ends there.
// The business I have choosen is a electonics store that sells in bulk, allows the user to purchase different products:
// laptops, phones, or consoles
#include <iostream>
#include <string>
using namespace std;

// base class || parent class
class Product
{
    // attributes of class product, given to all derived classes
protected:
    int productNumber;
    int quantity;
    int price;
    string productName;
    string manufacturer;
    //
public:
    // constructor of the class product if there has been an input
    Product(int productNumber, int quantity, int price,
            string productName, string manufacturer)
    {
        setProductNumber(productNumber);
        setProductQuantity(quantity);
        setProductPrice(price);
        setProductName(productName);
        setManufacturer(manufacturer);
    }
    // default constructor, if no input is provided
    Product()
    {
        setProductNumber(100);
        setProductQuantity(100);
        setProductPrice(1000);
        setProductName("default");
        setManufacturer("default");
    }

    // getters of attributes
    int getProductNumber()
    {
        return productNumber;
    }
    int getQuantity()
    {
        return quantity;
    }
    int getPrice()
    {
        return price;
    }
    string getProductName()
    {
        return productName;
    }
    string getManufacturer()
    {
        return manufacturer;
    }
    // setters of attributes
    void setProductNumber(int productNumber)
    {
        this->productNumber = productNumber;
    }
    void setProductQuantity(int quantity)
    {
        this->quantity = quantity;
    }
    void setProductPrice(int productPrice)
    {
        this->price = productPrice;
    }
    void setProductName(string productName)
    {
        this->productName = productName;
    }
    void setManufacturer(string manufacturer)
    {
        this->manufacturer = manufacturer;
    }

    // this funciton is virtual in order to use it in the derived classes
    virtual void info() = 0;

    // function to buy the product
    virtual void buyProducts(int) = 0;
};

// child class one:
class Laptop : public Product
{ // unique attributes
private:
    int ram;
    int storage;

public:
    // constructor, using the inherited paerent class constructor
    Laptop(int productNumber, int quantity, int price,
           string productName, string manufacturer, int ram, int storage)
        : Product(productNumber, quantity, price, productName, manufacturer)
    {
        this->ram = ram;
        this->storage = storage;
    }
    // setters and getters of the derived class:
    int getRam()
    {
        return ram;
    }
    int getStorage()
    {
        return storage;
    }
    // inherited from the base class and overwridden
    void info()
    {
        // display the inherited attributes from the base class product:
        cout << "informaiton about product" << endl;
        cout << "the product number is: " << getProductNumber() << endl;
        cout << "the quantity of the product is: " << getQuantity() << endl;
        cout << "the price of the product is: " << getPrice() << endl;
        cout << "the product name is: " << getProductName() << endl;
        cout << "the manufacturer of the product is: " << getManufacturer() << endl;
        // class unique attributes
        cout << "the ram of the laptop is: " << getRam() << endl;
        cout << "the storage of the laptop is: " << getStorage() << endl;
    }

    // to buy products, takes in the quanitity
    void buyProducts(int numberOfProducts)
    {
        int currentStock = getQuantity();                     // returns the quanitiy and stores it in current stock
        int remainingStock = currentStock - numberOfProducts; // calculates the remaining product
        int priceOfPurchase = numberOfProducts * getPrice();
        string productPurchasedName = getProductName();
        if (currentStock <= numberOfProducts) // if the stock is less than the number asked for
        {
            cout << "sorry it is not possible to fulfil your order please try a smaller quantity" << endl;
            return;
        }
        else
        {
            setProductQuantity(remainingStock); // sets the quantity to the stock left after completing the order
            cout << "you have successfully purchased: " << numberOfProducts << " of product: "
                 << productPurchasedName << ", at a cost of: " << priceOfPurchase << endl;
        }
    }
};
// second child class:
class Phone : public Product
{
private:
    int cameraPixles;
    string fiveG;

public:
    // constructor, uses the inherited parent class constructor
    Phone(int productNumber, int quantity, int price,
          string productName, string manufacturer, int cameraPixles, string fiveG)
        : Product(productNumber, quantity, price, productName, manufacturer)
    {
        this->cameraPixles = cameraPixles;
        this->fiveG = fiveG;
    }

    // getters of the class:
    int getCameraPixles()
    {
        return cameraPixles;
    }
    string getFiveG()
    {
        return fiveG;
    }
    // info funciton to display all the attributes in the class
    void info()
    {
        // display the inherited attributes from the base class product:
        cout << "informaiton about product" << endl;
        cout << "the product number is: " << getProductNumber() << endl;
        cout << "the quantity of the product is: " << getQuantity() << endl;
        cout << "the price of the product is: " << getPrice() << endl;
        cout << "the product name is: " << getProductName() << endl;
        cout << "the manufacturer of the product is: " << getManufacturer() << endl;
        cout << "the camera pixles of the phone is: " << getCameraPixles() << endl;
        cout << "the  phone is " << getFiveG() << " of using 5g" << endl;
    }
    void buyProducts(int numberOfProducts)
    {
        int currentStock = getQuantity();                     // returns the quanitiy and stores it in current stock
        int remainingStock = currentStock - numberOfProducts; // calculates teh remaining stock
        int priceOfPurchase = numberOfProducts * getPrice();
        string productPurchasedName = getProductName();
        if (currentStock <= numberOfProducts)
        {
            cout << "sorry it is not possible to fulfil your order please try a smaller quantity" << endl;
            return;
        }
        else
        {
            setProductQuantity(remainingStock); // sets the quantity to the stock left after completing the order
            cout << "you have successfully purchased: " << numberOfProducts << " of product: "
                 << productPurchasedName << ", at a cost of: " << priceOfPurchase << endl;
        }
    }
};
// third child class
class Console : public Product
{
private:
    int controlloerNumber;
    int NumberOfCds;

public:
    // class constructor
    Console(int productNumber, int quantity, int price,
            string productName, string manufacturer, int controllerNumber, int numberOfCds)
        : Product(productNumber, quantity, price, productName, manufacturer)
    {
        this->controlloerNumber = controllerNumber;
        this->NumberOfCds = NumberOfCds;
    }
    // getters of the class:
    int getNumberofControllers()
    {
        return controlloerNumber;
    }
    int getNumberOfCds()
    {
        return NumberOfCds;
    }
    // funcion to display the informaiton about the conosel
    void info()
    {
        cout << "Information about product" << endl;
        cout << "the product number is: " << getProductNumber() << endl;
        cout << "the quantity of the product is: " << getQuantity() << endl;
        cout << "the price of the product is: " << getPrice() << endl;
        cout << "the product name is: " << getProductName() << endl;
        cout << "the manufacturer of the product is: " << getManufacturer() << endl;
        cout << "the number of controllers sold with the console is: " << getNumberofControllers() << endl;
        cout << "the number of cds that are sold with the console: " << getNumberOfCds() << endl;
    }
    // funciton to buy products, reduces quantity of proudcts
    void buyProducts(int numberOfProducts)
    {
        int currentStock = getQuantity();                     // returns the quanitiy and stores it in current stock
        int remainingStock = currentStock - numberOfProducts; // calculates teh remaining stock
        int priceOfPurchase = numberOfProducts * getPrice();
        string productPurchasedName = getProductName();
        if (currentStock <= numberOfProducts)
        {
            cout << "sorry it is not possible to fulfil your order please try a smaller quantity" << endl;
            return;
        }
        else
        {
            setProductQuantity(remainingStock); // sets the quantity to the stock left after completing the order
            cout << "you have successfully purchased: " << numberOfProducts << " of product: "
                 << productPurchasedName << ", at a cost of: " << priceOfPurchase << endl;
        }
    }
};
int main()
{
    // creating the objects used
    Laptop laptop1 = Laptop(01, 10, 5500, "Legion", "Lenovo", 16, 1000);
    Phone Phone1 = Phone(2, 20, 3000, "Iphone X", "Apple", 20, "yes");
    Console console1 = Console(03, 50, 1000, "Ps5", "Sony", 2, 10);

    int optionNumber = 0;
    int running = 0;
    int choice = 0;
    int choice2 = 0;
    // welcome menu:
    cout << "Welcome to DigitalElectronics inc" << endl;
    cout << "---------Main Menu---------" << endl;
    cout << "Plese enter a number based on your choice" << endl;
    cout << "for information about the product press 1, to buy a prodect press 2" << endl;
    cin >> choice;
    // while the choice is not correct
    while (choice != 1 && choice != 2)
    {
        cout << "enter 1 or 2 only" << endl;
        // save choice
        cin >> choice;
    }
    // depending on the user choice he can be sent to menu 1 where he can view the proudcts, or menu two where he can buy the products
    if (choice == 1)
    {
        cout << "choose product, [P]hone, [L]aptop, [C]onsole" << endl;
        char product;
        cin >> product;
        // while no correct choice is inputted
        while (product != 'P' && product != 'L' && product != 'C')
        {
            cout << "enter P,L or C only" << endl;
            cin >> product;
        }
        switch (product) // switch for the user input
        {
        case 'P':
            Phone1.info(); // call the object info method
            break;
        case 'L':
            laptop1.info();
            break;
        case 'C':
            console1.info();
            break;
        default:
            break;
        }
    }
    if (choice == 2) // buy products menu
    {
        cout << "choose product, [P]hone, [L]aptop, [C]onsole" << endl;
        char product;
        cin >> product;
        while (product != 'P' && product != 'L' && product != 'C')
        {
            cout << "enter P,L or C only" << endl;
            cin >> product;
        }
        cout << "how many products do you want to buy?" << endl;
        int buy;
        cin >> buy;
        switch (product)
        {
            // depending on the user input he can buy different products
        case 'P':
            Phone1.buyProducts(buy);
            break;
        case 'L':
            laptop1.buyProducts(buy);
            break;
        case 'C':
            console1.buyProducts(buy);
            break;
        default:
            break;
        }
    }
}
