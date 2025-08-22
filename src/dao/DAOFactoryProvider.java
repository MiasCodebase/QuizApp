package dao;

/** Creates a fresh DAOFactory (and Connection) per unit-of-work. */
public interface DAOFactoryProvider {
    DAOFactory open();
}