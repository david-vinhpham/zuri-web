export const LAYOUT = {
  MAX_WIDTH: 768,
};

export const REGEXP = {
  PHONE_NUMBER: /^\d{10,11}$/,
  COUNTRY_CODE: /^\+\d{2,3}$/,
  VERIFY_CODE: /^\d{6}$/,
  ENCRYPT_PHONE: /([\w+]{4})(\d+)(\d{2})$/,
  TAX_NUMBER: /\d{9,12}/,
};

export const REGISTER = {
  USER_NAME: {
    LABEL: 'Tên quản lý',
    VALUE: 'userName',
    PLACEHOLDER: 'Nhập tên quản lý',
  },
  SHOP_NAME: {
    LABEL: 'Tên cửa hàng',
    VALUE: 'shopName',
    PLACEHOLDER: 'Nhập tên cửa hàng',
  },
  PHONE_NUMBER: {
    TYPE: 'tel',
    LABEL: 'Số điện thoại',
    VALUE: 'phoneNumber',
    PLACEHOLDER: 'Nhập số điện thoại',
  },
  SHOP_ADDRESS: {
    LABEL: 'Địa chỉ cửa hàng',
    VALUE: 'shopAddress',
    PLACEHOLDER: 'Nhập địa chỉ cửa hàng',
  },
  BUSINESS_TYPE: {
    LABEL: 'Mô hình kinh doanh',
    VALUE: 'businessType',
    PLACEHOLDER: 'Nhập mô hình kinh doanh',
  },
  CATEGORY_TYPE: {
    LABEL: 'Danh mục sản phẩm',
    VALUE: 'categoryType',
    PLACEHOLDER: 'Nhập danh mục sản phẩm',
  },
  EMAIL: {
    TYPE: 'email',
    LABEL: 'Email',
    VALUE: 'userEmail',
    PLACEHOLDER: 'Nhập địa chỉ email',
  },
};

export const TAX = {
  CO_NAME: {
    LABEL: 'Tên công ty:',
    VALUE: 'coName',
    PLACEHOLDER: 'Nhập tên công ty',
  },
  CO_ADDRESS: {
    LABEL: 'Địa chỉ công ty:',
    VALUE: 'coAddress',
    PLACEHOLDER: 'Nhập địa chỉ công ty',
  },
  BRANCH_NAME: {
    LABEL: 'Chi nhánh (không bắt buộc):',
    VALUE: 'coBranch',
    PLACEHOLDER: 'Nhập tên chi nhánh',
  },
  TAX_NUMBER: {
    TYPE: 'tel',
    LABEL: 'Mã số thuế:',
    VALUE: 'taxNumber',
    PLACEHOLDER: 'Nhập mã số thuế',
  },
  REGISTER_NUMBER: {
    TYPE: 'tel',
    LABEL: 'Số đăng ký (không bắt buộc):',
    VALUE: 'registerNumber',
    PLACEHOLDER: 'Nhập số đăng ký',
  },
};

export const SHOP_DETAIL = {
  CATEGORY: {
    BUSINESS: {
      NAME: 'Cửa hàng & Loại sản phẩm',
      MENU: [
        {
          LABEL: 'Mô hình kinh doanh',
          VALUE: 'businessName',
          PLACEHOLDER: 'Chọn mô hình kinh doanh',
          DROPDOWN: true,
        },
        {
          LABEL: 'Doanh mục sản phẩm',
          VALUE: 'BusinessType',
          PLACEHOLDER: 'Chọn sản phẩm kinh doanh',
          GUTTER: true,
        },
      ],
    },
    INFO: {
      NAME: 'Thông tin cửa hàng',
      MENU: [
        {
          LABEL: 'Tên cửa hàng',
          VALUE: 'shopName',
          PLACEHOLDER: 'Nhập tên cửa hàng',
        },
        {
          TYPE: 'tel',
          LABEL: 'Số điện thoại',
          VALUE: 'phoneNumber',
          PLACEHOLDER: 'Nhập số điện thoại',
        },
        {
          LABEL: 'Địa chỉ',
          VALUE: 'shopAddress',
          PLACEHOLDER: 'Nhập địa chỉ cửa hàng',
          GUTTER: true,
        },
      ],
    },
    BRANCH: {
      NAME: 'Thông tin chi nhánh',
      MENU: [
        {
          LABEL: 'Tên quản lý',
          VALUE: 'branchName',
          PLACEHOLDER: 'Nhập tên quản lý chi nhánh',
        },
        {
          TYPE: 'tel',
          LABEL: 'Số điện thoại',
          VALUE: 'branchPhoneNumber',
          PLACEHOLDER: 'Nhập số điện thoại',
        },
        {
          TYPE: 'email',
          LABEL: 'Email',
          VALUE: 'branchEmail',
          PLACEHOLDER: 'Nhập địa chỉ email',
          GUTTER: true,
        },
      ],
    },
  },
};

export const TABS = {
  GENERAL: {
    NAME: 'tổng quan',
    URL: '/home',
  },
  REPORT: {
    NAME: 'báo cáo',
    URL: '/report',
  },
  ACTIVITY: {
    NAME: 'hoạt động',
    URL: '/activity',
  },
  SHOP: {
    NAME: 'cửa hàng',
    URL: '/shop',
  },
};
