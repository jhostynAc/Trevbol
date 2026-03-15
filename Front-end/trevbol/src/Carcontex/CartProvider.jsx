import { createContext, useState, useEffect } from "react";

export const Cartcontext = createContext();

export function CartProvider({ children }) {
    const [allproducts, setAllproducts] = useState(() => {
        const saved = localStorage.getItem("carrito");
        return saved ? JSON.parse(saved) : [];
    });

    const total = allproducts.reduce((acc, product) => {
        return acc + product.precio * product.quantity;
    }, 0);

    const contProduct = allproducts.reduce((acc, product) => {
        return acc + product.quantity;
    }, 0);

    useEffect(() => {
        localStorage.setItem("carrito", JSON.stringify(allproducts));
    }, [allproducts]);

    const updateQuantity = (id, size, nuevaCantidad) => {
        if (nuevaCantidad < 1) return;
        const updatedProducts = allproducts.map(product =>
            product.id === id && product.size === size
                ? { ...product, quantity: nuevaCantidad }
                : product
        );
        setAllproducts(updatedProducts);
    };

    // Quitamos los Swal de aquí, la validación se hace en el componente
    function addtoCart(product, tallaSeleccionada) {
        setAllproducts(prev => {
            const exist = prev.find(
                item => item.id === product.id && item.size === tallaSeleccionada
            );
            if (exist) {
                return prev.map(item =>
                    item.id === product.id && item.size === tallaSeleccionada
                        ? { ...item, quantity: item.quantity + 1 } : item
                )
            }
            return [...prev, { ...product, size: tallaSeleccionada, quantity: 1 }];
        });
    }

    function removeFromCart(id, size) {
        setAllproducts(prev =>
            prev.filter(item => !(item.id === id && item.size === size))
        );
    }

    function cleanCart() {
    setAllproducts([]);
    localStorage.removeItem("carrito");
}
   return (
    <Cartcontext.Provider value={{
        allproducts,
        total,
        contProduct,
        addtoCart,
        removeFromCart,
        cleanCart, 
        updateQuantity
    }}>
        {children}
    </Cartcontext.Provider>
);
}