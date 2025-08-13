/**
 * The {@code gui.model} package contains classes that hold and organize
 * data from the service layer in forms optimized for the Swing-based
 * presentation layer.
 *
 * <p>Its responsibilities include:</p>
 * <ul>
 *   <li>Storing lightweight, presentation-friendly versions of domain
 *       DTOs that include usually an ID plus a title, and where applicable,
 *       IDs referencing related entities, to support unique identification
 *       and simple interaction in the GUI.</li>
 *   <li>Organizing aggregated service-layer DTOs into lookup-efficient
 *       structures (e.g., maps keyed by IDs) to simplify data access
 *       in the presentation logic.</li>
 *   <li>Maintaining session-level state that the GUI needs to persist
 *       across multiple interactions, without cluttering panel fields or
 *       overloading controllers.</li>
 * </ul>
 *
 * <p>This separation allows GUI components to remain simple and focused
 * on rendering, while {@code SessionData} holds session and lookup data,
 * and the controller handles data retrieval from the service layer
 * and transformation into GUI-friendly forms.</p>
 */
package gui.model;