/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.Book;
import model.BookModel;
import view.BookView;

public class BookController implements Action {
	public static BookView view;
	public TableRowSorter<DefaultTableModel> sorter;
	public BookModel model;

	public BookController(BookView view) {
		this.view = view;
		this.model = view.model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acctionCommand = e.getActionCommand();
		switch (acctionCommand) {
			case "Insert":
				//alert();
				addBook();
				break;
			case "Update":
				update();
				break;
			case "Delete":
				delete();
				break;
			case "Cancel":
				resetForm();
				break;
			case "Search":
				search();
				break;
			case "Clear":
				clearFilter();
				break;
			case "Open File":
				importFile();
				break;
			case "Upload File":
				importFile();
				break;
			case "Save":
				exportFile();
				break;
			case "Exit":
				exit();
				break;
			default:
				break;
		}
	}

//	public void alert() {
//		if (view.textFieldID.getText().isEmpty()
//		&& view.textFieldTitle.getText().isEmpty()
//		&& view.textFieldPrice.getText().isEmpty()
//		&& view.textFieldAuthor.getText().isEmpty()
//		&& view.textFieldPublisher.getText().isEmpty()
//		&& view.textFieldPublicationTime.getText().isEmpty()) {
//		
//                    JOptionPane.showMessageDialog(view, "All fields are not empty");
//                    view.textFieldID.requestFocus();
//                    return;
//                } 
//                else if (view.textFieldID.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(view, "ID is not empty");
//			view.textFieldID.requestFocus();
//			return;
//		} else if (view.textFieldTitle.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(view, "Title is not empty");
//			view.textFieldTitle.requestFocus();
//			return;
//		} else if (view.textFieldPrice.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(view, "Price is not empty");
//			view.textFieldPrice.requestFocus();
//			return;
//		} else if (view.textFieldAuthor.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(view, "Author is not empty");
//			view.textFieldAuthor.requestFocus();
//			return;
//		} else if (view.textFieldPublisher.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(view, "Publisher is not empty");
//			view.textFieldPublisher.requestFocus();
//			return;
//		} else if (view.textFieldPublicationTime.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(view, "Publication time is not empty");
//			view.textFieldPublicationTime.requestFocus();
//			return;
//		} else {
//			return;
//		}
//	}

	public Book getBookfromTable() {
		String ID = view.textFieldID.getText();
		String title = view.textFieldTitle.getText();
		String author = view.textFieldAuthor.getText();
		Date publicationTime = new Date(view.textFieldPublicationTime.getText());
		String publisher = view.textFieldPublisher.getText();
		float price = 0;
		try {
			price = Float.parseFloat(view.textFieldPrice.getText());
			if (price < 0) 
                        {
			JOptionPane.showMessageDialog(null, "The price must be greater than 0.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "The price must be a number.");
		}
		return new Book(ID, title, price, author, publicationTime, publisher);
	}

	public void addBook() {
            if (view.textFieldID.getText().isEmpty()
                                && view.textFieldTitle.getText().isEmpty()
                                && view.textFieldPrice.getText().isEmpty()
                                && view.textFieldAuthor.getText().isEmpty()
                                && view.textFieldPublisher.getText().isEmpty()
                                && view.textFieldPublicationTime.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "All fields are empty");
                view.textFieldID.requestFocus();
                return; }
            else{   
	try {
		String ID = view.textFieldID.getText();
		String title = view.textFieldTitle.getText();
		String author = view.textFieldAuthor.getText();
                String publisher = view.textFieldPublisher.getText();
		Date publicationTime = null;
                                              
                try{
                    if(ID.isEmpty()){
                        throw new Exception();}
                    } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, "ID is empty");
                    return;
                } 
                try{
                    if(title.isEmpty()){
                        throw new Exception();}
                    } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Title is empty");
                    return;
                }
                try{
                    if(author.isEmpty()){
                        throw new Exception();}
                    } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Author is empty");
                    return;
                }
                float price = 0;
		try 
                {
                    price = Float.parseFloat(view.textFieldPrice.getText());
                    if (price < 0) 
                    {
                        JOptionPane.showMessageDialog(null, "The price must be greater than 0.");
                        return;
                    }
		} catch (NumberFormatException e) 
                {JOptionPane.showMessageDialog(null, "The price must be a number.");
                return;}
                
                try {
                    if(publisher.isEmpty())
                        throw new Exception();
                }
                catch (Exception e) {JOptionPane.showMessageDialog(null, "Publisher is empty"); return;}
                
		try {
                    publicationTime = new Date(view.textFieldPublicationTime.getText());
		} 
                catch (IllegalArgumentException e) 
                {
                    JOptionPane.showMessageDialog(null, "The publication time must be in the format dd/MM/yyyy.");
                    return;
		}
                
             
		Book book = new Book(ID, title, price, author, publicationTime, publisher);
		DefaultTableModel model_Table = (DefaultTableModel) view.table.getModel();
		if (model.isExist(book)) {
                    JOptionPane.showMessageDialog(view, "The book already exists.");
                    return;
			} else {
		try {
		model_Table.addRow(new Object[] { book.getID(),book.getTitle(), Float.valueOf(book.getPrice()),
                                    book.getAuthor(),book.getPublisher(),book.getPublicationTime() });
		model.insert(book);
		JOptionPane.showMessageDialog(view, "Add book successfully.");
				} 
                catch (Exception e) {
                    JOptionPane.showMessageDialog(view, "Add book failed.");
				}
			}
		} 
                    catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(view, "Add book failed.");
		}
	}
        }

	public void update() {
		try {
                    String ID = view.textFieldID.getText();
                    String title = view.textFieldTitle.getText();
                    String author = view.textFieldAuthor.getText();
                    String publisher = view.textFieldPublisher.getText();
                    Date publicationTime = null;
                                              
                try{
                    if(ID.isEmpty()){
                        throw new Exception();}
                    } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, "ID is empty");
                    return;
                } 
                try{
                    if(title.isEmpty()){
                        throw new Exception();}
                    } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Title is empty");
                    return;
                }
                try{
                    if(author.isEmpty()){
                        throw new Exception();}
                    } 
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Author is empty");
                    return;
                }
                float price = 0;
		try 
                {
                    price = Float.parseFloat(view.textFieldPrice.getText());
                    if (price < 0) 
                    {
                        JOptionPane.showMessageDialog(null, "The price must be greater than 0.");
                        return;
                    }
		} catch (NumberFormatException e) 
                {JOptionPane.showMessageDialog(null, "The price must be a number.");
                return;}
                try {
                    if(publisher.isEmpty())
                        throw new Exception();
                }
                catch (Exception e) {JOptionPane.showMessageDialog(null, "Publisher is empty"); return;}
                
		try {
                    publicationTime = new Date(view.textFieldPublicationTime.getText());
		} 
                catch (IllegalArgumentException e) 
                {
                    JOptionPane.showMessageDialog(null, "The publication time must be in the format dd/MM/yyyy.");
                    return;
		}
                        Book book = new Book(ID, title, price, author, publicationTime, publisher);
			DefaultTableModel model_Table = (DefaultTableModel) view.table.getModel();
			int i = view.table.getSelectedRow();
			int[] rows = view.table.getSelectedRows();
			if (rows.length == 0) {
				JOptionPane.showMessageDialog(view, "Please select a row to update.");
				return;
			}
			if (sorter != null) {
				int rowInModel = sorter.convertRowIndexToModel(i);
				i = rowInModel;
			}
			if (i < 0) {
				JOptionPane.showMessageDialog(view, "Please select a row to update.");
				return;
			} else {
                            
				Book books = getBookfromTable();
				model_Table.setValueAt(books.getID(), i, 0);
				model_Table.setValueAt(books.getTitle(), i, 1);
				model_Table.setValueAt(Float.valueOf(book.getPrice()), i, 2);
				model_Table.setValueAt(books.getAuthor(), i, 3);
				model_Table.setValueAt(books.getPublisher(), i, 4);
				model_Table.setValueAt(books.getPublicationTime(), i, 5);
				model.updateBook(book, i);
				JOptionPane.showMessageDialog(view, "Update successfully.");
			}
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Add book failed.");
			return;
		}
	}

	public void delete() {
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		int[] rows = this.view.table.getSelectedRows();
		if (rows.length == 0) {
			JOptionPane.showMessageDialog(view, "Please select a row to delete.");
			return;
		}
		if (sorter != null) {
			for (int i = 0; i < rows.length; i++) {
				int rowDiff = sorter.convertRowIndexToModel(rows[i]);
				rows[i] = rowDiff;
			}
		}
		int option = JOptionPane.showConfirmDialog(view, "Do you want to delete this book?");
		if (option == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(view, "Delete failed.");
		} else if (option == JOptionPane.YES_OPTION) {
			for (int i = 0; i < rows.length; i++) {
				model_table.removeRow(rows[i] - i);
				model.deleteBook(rows[i] - i);
			}
			JOptionPane.showMessageDialog(view, "Delete successfully.");
		}
		resetForm();
	}

	public void search() {          
		if (view.textFieldSearch.getText().isEmpty()) {
			JOptionPane.showMessageDialog(view, "Please enter the specified ID.");
			view.textFieldSearch.requestFocus();
		} else {
			String search = view.textFieldSearch.getText();
			RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(search, 0);
			DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
			sorter = new TableRowSorter<>(model_table);
			sorter.setRowFilter(rf);
			view.table.setRowSorter(sorter);
		}
	}

	public void clearFilter() {
		view.textFieldSearch.setText("");
		RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("", 0);
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		sorter = new TableRowSorter<>(model_table);
		sorter.setRowFilter(rf);
		view.table.setRowSorter(sorter);
	}

	public void exit() {
		int option = JOptionPane.showConfirmDialog(view, "Do you want to exit this session?", "Exit",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void saveFile(String path) {
		try {
			this.model.setFileName(path);
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (Book book : this.model.getBooks()) {
				oos.writeObject(book);
                        JOptionPane.showMessageDialog(view, "Save file successfully!");
                                
			}
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
//Nó giúp để theo dõi ngoại lệ. 
////Ví dụ, bạn đang viết một số phương thức trong chương trình của bạn và một trong những phương thức của bạn gây ra lỗi. 
////Sau đó printstack sẽ giúp bạn xác định phương pháp nào gây ra lỗi. Stack sẽ giúp như thế này:
////Đầu tiên phương thức chính của bạn sẽ được gọi và chèn vào ngăn xếp, 
////sau đó phương thức thứ hai sẽ được gọi và chèn vào ngăn xếp theo thứ tự LIFO 
//và nếu có lỗi xảy ra ở đâu đó bên trong bất kỳ phương thức nào thì ngăn xếp này sẽ giúp xác định phương thức đó.
		}
	}

	public void exportFile() {
		if (this.model.getFileName().length() > 0) {
			saveFile(this.model.getFileName());
                        
                        return;
		} else {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				saveFile(file.getAbsolutePath());
			}
		}
	}
            //Export bao gồm chức năng save và xuất file ra.
            //Khi đang mở 1 file lên để làm việc thì ấn save sẽ lưu dữ liệu vào file đó.
            //Trong trường hợp đang kh mở file nào thì Save có chức năng Export file đó ra.
	public void importFile() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				openFile(file);
				reloadTable();
			}
			JOptionPane.showMessageDialog(view, "Import file successfully!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Import file failed!");
		}
	}

	public void openFile(File file) {
		ArrayList<Book> book = new ArrayList<Book>();
		try {
			this.model.setFileName(file.getAbsolutePath());
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Book bk = null;
			while ((bk = (Book) ois.readObject()) != null) {
				book.add(bk);
			}
			ois.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.model.setBooks(book);
	}

	public void reloadTable() {
		while (true) {
			DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
			int rowNum = model_table.getRowCount();
			if (rowNum == 0)
				break;
			else
				try {
					model_table.removeRow(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		for (Book book : this.model.getBooks()) {
			try {
				model_table.addRow(new Object[] { book.getID(),
						book.getTitle(),
						Float.valueOf(book.getPrice()),
						book.getAuthor(),
						book.getPublisher(),
						book.getPublicationTime() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void resetForm() {
		view.textFieldID.setText("");
		view.textFieldTitle.setText("");
		view.textFieldPrice.setText("");
		view.textFieldAuthor.setText("");
		view.textFieldPublisher.setText("");
		view.textFieldPublicationTime.setText("");
		RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("", 0);
		DefaultTableModel model_table = (DefaultTableModel) this.view.table.getModel();
		sorter = new TableRowSorter<>(model_table);
		sorter.setRowFilter(rf);
		view.table.setRowSorter(sorter);
	}

	public static void addBookToTable(Book book) {
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		model_table.addRow(new Object[] { book.getID(),
				book.getTitle(),
				Float.valueOf(book.getPrice()),
				book.getAuthor(),
				book.getPublisher(),
				book.getPublicationTime() });
	}

	public static void updateToTable(Book book) {
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		model_table.setValueAt(book.getID(), view.table.getSelectedRow(), 0);
		model_table.setValueAt(book.getTitle(), view.table.getSelectedRow(), 1);
		model_table.setValueAt(Float.valueOf(book.getPrice()), view.table.getSelectedRow(), 2);
		model_table.setValueAt(book.getAuthor(), view.table.getSelectedRow(), 3);
		model_table.setValueAt(book.getPublisher(), view.table.getSelectedRow(), 4);
		model_table.setValueAt(book.getPublicationTime(), view.table.getSelectedRow(), 5);
	}

	public static void deleteBook() {
		DefaultTableModel model_table = (DefaultTableModel) view.table.getModel();
		model_table.removeRow(view.table.getSelectedRow());
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	public void updateBook(Book book) {
		for (Book bk : this.model.getBooks()) {
			if (bk.getID().equals(book.getID())) {
				bk.setTitle(book.getTitle());
				bk.setPrice(book.getPrice());
				bk.setAuthor(book.getAuthor());
				bk.setPublisher(book.getPublisher());
				bk.setPublicationTime(new Date(view.textFieldPublicationTime.getText()));
			}
		}
		reloadTable();
	}

}