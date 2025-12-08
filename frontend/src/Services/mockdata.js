
const staffList = [
  {
    staff_id: 1,
    staff_name: "Alice Johnson",
    staff_nrc: "1/ABCD(N)123456",
    staff_hiredate: "01-01-2025",
    staff_phone: "09123456789",
    staff_role: "ADMIN"},
  {
    staff_id: 2,
    staff_name: "Bob Smith",
    staff_nrc: "2/EFGH(N)987654",
    staff_hiredate: "15-03-2024",
    staff_phone: "09876543210",
    staff_role: "WAITER"},
    {
    staff_id: 3,
    staff_name: "Charlie Brown",
    staff_nrc: "3/IJKL(N)456789",
    staff_hiredate: "20-06-2023",
    staff_phone: "09223344556",
    staff_role: "CASHIER"}];
const staffInfo = {
    staff_id: 1,
    staff_login_name: "alicej",
    staff_password: "password123",
    staff_name: "Alice Johnson",
    staff_nrc: "1/ABCD(N)123456",
    staff_dob: "1990-05-15",
    staff_hiredate: "01-01-2025",
    staff_address: "123 Main St, Cityville",
    staff_phone: "09123456789",
    staff_role: "ADMIN"};

const staffRoles = [ "ADMIN", "WAITER", "CASHIER" ];
const menuList = [
    { 
        menu_id: 1,
        menu_name: "Margherita Pizza",
        menu_price: 8.99,
        menu_description: "Classic pizza with tomato sauce, mozzarella, and fresh basil.",
        menu_category: "MAIN_COURSE"
    },
    {
        menu_id: 2,
        menu_name: "Caesar Salad",
        menu_price: 5.99,
        menu_description: "Crisp romaine lettuce with Caesar dressing, croutons, and Parmesan cheese.",
        menu_category: "APPETIZER"
    }];
const menuCategories = [ "APPETIZER", "MAIN_COURSE", "DESSERT", "BEVERAGE" ];
const menuInfo = {
    menu_id: 1,
    menu_name: "Margherita Pizza",
    menu_price: 8.99,
    menu_description: "Classic pizza with tomato sauce, mozzarella, and fresh basil.",
    menu_category: "MAIN_COURSE"
};
const slipList = [
    {
        slip_id: 1,
        slip_date: "2024-10-01",
        slip_total: 45.50,
        slip_status: "PAID",
        cashier : {
            staff_id: 3,
            staff_name: "Charlie Brown"
        }
    },
    {
        slip_id: 2,
        slip_date: "2024-10-02",
        slip_total: 30.00,
        slip_status: "ONGOING",
        cashier : {}
    }
];
const slipInfo = {
    slip_id: 1,
    slip_date: "2024-10-01",
    slip_total: 45.50,
    tax_rate : 0.05,
    service_rate : 0.10,
    slip_paid_amount : 50.00,
    slip_change_amount : 4.50,
    slip_status: "PAID",
    cashier : { staff_id: 3, staff_name: "Charlie Brown" },
    order_menus : [
        {   
            menu_id: 1,
            menu_name: "Margherita Pizza",
            menu_price: 8.99,
            quantity: 2
        },
        {   
            menu_id: 2,
            menu_name: "Caesar Salad",
            menu_price: 5.99,   
            quantity: 1
        }
    ]
};
const createdSlip = {
    slip_id: 3,
    slip_date: "2024-10-03",
    slip_total: 0.00,
    slip_status: "ONGOING",
    cashier : {}
};
const layoutList = [
    {
        layout_id: 1,
        layout_name: "Main Dining Area",
        layout_status: "ACTIVE",
        layout_deleted: false
    },
    {
        layout_id: 2,
        layout_name: "Outdoor Patio",
        layout_status: "INACTIVE",
        layout_deleted: false
    }
];
const layoutInfo = {
    layout_id: 1,
    layout_name: "Main Dining Area",
    layout_status: "ACTIVE",
    layout_deleted: false,
    tables: [
        {
            table_id: 1,
            table_name: "Table 1",
            table_structure: {
                x: 50,
                y: 100,
                width: 80,
                height: 80,
                angle: 0
            },
            table_status: "AVAILABLE"
        },
        {
            table_id: 2,
            table_name: "Table 2",
            table_structure: {
                x: 200,
                y: 100,
                width: 80,  
                height: 80,
                angle: 0
            },
            table_status: "OCCUPIED"
        }
    ]
};
const layoutCreated = 
{
    message : "Layout created successfully",
    layout_id: 3,
    layout_name: "New Layout",
    layout_status: "ACTIVE",
    layout_deleted: false
};
const layoutUpdated = 
{
    message : "Layout updated successfully",
    layout_id: 1,
    layout_name: "Main Dining Area - Updated",
    layout_status: "ACTIVE",
    layout_deleted: false
};
const availableTable = [
    {
        table_id: 1,
        table_name: "Table 1",
        layout_name : "Main Dining Area"
    },
    { 
        table_id: 3,
        table_name: "Table 3",
        layout_name : "Outdoor Patio"
    }
];

const occupiedTable = [
    {
        table_id: 2,   
        table_name: "Table 2",
        layout_name : "Main Dining Area"
    }
];

const slipOrders = [
    {
        order_id: 1,
        menu_id: 1,
        menu_name: "Margherita Pizza",
        menu_price: 8.99,
        quantity: 2,
        order_status: "SERVED",
        order_time: "2024-10-01T12:00:00Z"
    },
    {
        order_id: 2,
        menu_id: 2,
        menu_name: "Caesar Salad",
        menu_price: 5.99,
        quantity: 1,
        order_status: "IN_PROGRESS",
        order_time: "2024-10-01T12:05:00Z"
    }
];

const orderCreated = {
    order_id : 3,
    menu_id : 1,
    menu_name : "Margherita Pizza",
    menu_price : 8.99,
    quantity : 1,
    order_status : "PENDING",
    order_time : "2024-10-03T13:00:00Z"
};

const adminDashboard = {
    active_staff : 15,
    today_slips : 10,
    today_totalamount : 250.75,
    weekly_totalslips : 
    {
        "2024-09-24": 5,
        "2024-09-25": 8,
        "2024-09-26": 7,
        "2024-09-27": 10,
        "2024-09-28": 6,
        "2024-09-29": 9,
        "2024-09-30": 11
    },
    top_menus : [
        { menu_name: "Margherita Pizza", order_count: 25 },
        { menu_name: "Caesar Salad", order_count: 18 },
        { menu_name: "Spaghetti Bolognese", order_count: 15 }
    ]
};

export const mockdata = {
    
    admin_dashboard : adminDashboard,
    staff_list : staffList,
    staff_info : staffInfo,
    staff_roles : staffRoles,
    menu_list : menuList,
    menu_categories : menuCategories,
    menu_info : menuInfo,
    slip_list : slipList,
    slip_info : slipInfo,
    created_slip : createdSlip,
    layout_list : layoutList,
    layout_info : layoutInfo,
    layout_created : layoutCreated,
    layout_updated : layoutUpdated,
    available_table : availableTable,
    occupied_table : occupiedTable,
    slip_orders : slipOrders,
    order_created : orderCreated,

}
